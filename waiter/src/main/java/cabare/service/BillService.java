package cabare.service;

import static cabare.entity.domain.PayStatus.AWAIT;
import static cabare.entity.domain.PayStatus.PAID;

import cabare.repository.BillRepository;
import cabare.dto.BillDto;
import cabare.dto.BillPrint;
import cabare.dto.OrderIn;
import cabare.dto.OrderPrint;
import cabare.entity.domain.Money;
import cabare.entity.domain.PayType;
import cabare.entity.model.BillWaiter;
import cabare.entity.model.Cabare;
import cabare.entity.model.Discount;
import cabare.entity.model.DishWaiter;
import cabare.entity.model.Employee;
import cabare.entity.model.OrderItem;
import cabare.exception.BillAllreadyClosedException;
import cabare.exception.BillNotFoundException;
import cabare.exception.DeniedException;
import cabare.exception.EmptyOrderListException;
import cabare.exception.NoCheckPrintException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillService {

  @Autowired
  private EmployeeService employeeService;
  @Autowired
  private TimeService timeService;
  @Autowired
  private BillRepository billRepository;
  @Autowired
  private DishService dishService;
  @Autowired
  private OrderCounterService counterService;
  @Autowired
  private SecurityService securityService;
  @Autowired
  private DiscountService discountService;

  @Transactional
  public List<OrderPrint> openBill(BillDto billDto) {
    Employee employee = securityService.getEmployeeFromSession();
    List<OrderItem> orderItems = orderInsToOrderItems(billDto.getOrderIns(), employee.getCabare());
    LocalDateTime currentTime = timeService.getCurrentTime();

    BillWaiter billWaiter = new BillWaiter();
    billWaiter.setNumberOfPersons(billDto.getNumberOfPersons());
    billWaiter.setTableNumber(billDto.getTableNumber());
    billWaiter.setSaleType(billDto.getSaleType());
    billWaiter.addOrderItems(orderItems);

    billWaiter.setEmployee(employee);
    billWaiter.setOpenBillTime(currentTime);
    billWaiter.setOpened(true);
    billWaiter.setActiveShift(true);
    billWaiter.setMoneyPaid(Money.ZERO);
    billWaiter.setPayStatus(AWAIT);
    billWaiter.setCabare(employee.getCabare());
    billWaiter = billRepository.save(billWaiter);

    Optional<BillWaiter> first = billRepository
        .getBillByOpenedTable(employee, billDto.getTableNumber(), new PageRequest(0, 2))
        .getContent()
        .stream()
        .filter(b -> b.getServedTable() != null)
        .findFirst();
    if (first.isPresent()) {
      BillWaiter billSliced = first.get();
      billWaiter.setServedTable(billSliced.getServedTable());
    } else {
      billWaiter.setServedTable(billWaiter.getId());
    }
    billRepository.save(billWaiter);

    return billWaiter.getOrderItems().stream()
        .map(orderItem -> new OrderPrint(orderItem))
        .collect(Collectors.toList());
  }

  private List<OrderItem> orderInsToOrderItems(List<OrderIn> orderIns, Cabare cabare) {
    if (orderIns == null) {
      return Collections.EMPTY_LIST;
    }
    Long orderNumber = counterService.nextOrderNumber(cabare);
    return orderIns.stream()
        .filter(orderIn -> orderIn.getQuantity() != null && orderIn.getQuantity() > 0)
        .map(orderIn -> {
              DishWaiter dishWaiter = dishService.findByid(orderIn.getDishId());
              return new OrderItem(dishWaiter, orderIn.getQuantity(), orderIn.getComments(), orderNumber);
            }
        ).collect(Collectors.toList());
  }

  @Transactional
  public List<OrderPrint> addOrders(long billId, List<OrderIn> orderIns) {
    if (orderIns.isEmpty()) {
      throw new EmptyOrderListException();
    }
    BillWaiter billWaiter = getBill(billId);
    if (!billWaiter.isOpened()) {
      throw new BillAllreadyClosedException();
    }
    Employee employee = securityService.getEmployeeFromSession();
    if (!employee.getId().equals(billWaiter.getEmployee().getId())) {
      throw new DeniedException();
    }
    List<OrderItem> orderItems = orderInsToOrderItems(orderIns, employee.getCabare());
    billWaiter.addOrderItems(orderItems);
    billWaiter = billRepository.save(billWaiter);

    return billWaiter.getOrderItems().stream()
        .map(orderItem -> new OrderPrint(orderItem))
        .collect(Collectors.toList());
  }

  private BillWaiter getBill(long billId) {
    Employee employee = securityService.getEmployeeFromSession();
    Cabare cabare = employee.getCabare();
    return billRepository.findByIdAndCabare(billId, cabare)
        .orElseThrow(() -> new BillNotFoundException());
  }

  @Transactional
  public BillPrint print(Long billId, Long discountId) {
    BillWaiter billWaiter = getBill(billId);
    if (billWaiter.isOpened()) {
      if (discountId != null) {
        Discount discount = discountService.findById(discountId);
        if (discount.isActivated()) {
          billWaiter.setDiscount(discount);
          Money totalPrice = billWaiter.getTotalPrice();
          int discountSize = billWaiter.getDiscount().getSize();
          Money discountedSum = totalPrice.multiply(discountSize / 100f);
          Money toPaid = totalPrice.subtract(discountedSum);
          billWaiter.setMoneyDiscounted(discountedSum);
          billWaiter.setMoneyPaid(toPaid);
        }
      } else {
        billWaiter.setDiscount(null);
        Money totalPrice = billWaiter.getTotalPrice();
        billWaiter.setMoneyPaid(totalPrice);
        billWaiter.setMoneyDiscounted(Money.ZERO);
      }
      billWaiter.setPayStatus(AWAIT);
      billRepository.save(billWaiter);
    }
    return new BillPrint(billWaiter);
  }

  public void close(Long billId, PayType payType) {
    Employee employeeFromSession = securityService.getEmployeeFromSession();
    BillWaiter billWaiter = getBill(billId);
    if (!billWaiter.getEmployee().getId().equals(employeeFromSession.getId())) {
      throw new DeniedException();
    }
    if (billWaiter.getMoneyPaid() == Money.ZERO) {
      throw new NoCheckPrintException();
    }
    billWaiter.setPayType(payType);
    billWaiter.setPayStatus(PAID);
    billWaiter.setOpened(false);
    billWaiter.setCloseBillTime(timeService.getCurrentTime());
    billRepository.save(billWaiter);
  }

  public List<BillPrint> getOpened() {
    Employee employee = securityService.getEmployeeFromSession();
    return billRepository.findOpenedByEmployee(employee)
        .stream()
        .map(item -> new BillPrint(item))
        .collect(Collectors.toList());
  }

  List<BillWaiter> getCurrentShiftBills(Employee employee) {
    return billRepository.findCurrentShiftBillsByEmployee(employee);
  }

  public List<BillWaiter> findBillsByPeriodAndEmployee(LocalDate startDate, LocalDate endDate,
      Employee employee) {
    return billRepository.findBillsByPeriodAndEmployee(
        startDate.atStartOfDay()
        , endDate.atStartOfDay()
        , employee);
  }
}