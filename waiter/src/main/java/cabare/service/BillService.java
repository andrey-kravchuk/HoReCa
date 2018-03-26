package cabare.service;

import static cabare.entity.domain.PayStatus.AWAIT;
import static cabare.entity.domain.PayStatus.PAID;

import cabare.data.BillRepository;
import cabare.dto.BillDto;
import cabare.dto.BillPrint;
import cabare.dto.OrderIn;
import cabare.dto.OrderPrint;
import cabare.entity.domain.Money;
import cabare.entity.domain.PayType;
import cabare.entity.model.Bill;
import cabare.entity.model.Discount;
import cabare.entity.model.Dish;
import cabare.entity.model.Employee;
import cabare.entity.model.OrderItem;
import cabare.exception.BillAllreadyClosedException;
import cabare.exception.BillNotFoundException;
import cabare.exception.DeniedException;
import cabare.exception.EmptyOrderListException;

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
    List<OrderItem> orderItems = orderInsToOrderItems(billDto.getOrderIns());
    LocalDateTime currentTime = timeService.getCurrentTime();

    Bill bill = new Bill();
    bill.setNumberOfPersons(billDto.getNumberOfPersons());
    bill.setTableNumber(billDto.getTableNumber());
    bill.setSaleType(billDto.getSaleType());
    bill.addOrderItems(orderItems);

    bill.setEmployee(employee);
    bill.setOpenBillTime(currentTime);
    bill.setOpened(true);
    bill.setActiveShift(true);
    bill.setMoneyPaid(Money.ZERO);
    bill.setPayStatus(AWAIT);
    bill = billRepository.save(bill);

    Optional<Bill> first = billRepository
        .getBillByOpenedTable(employee, billDto.getTableNumber(), new PageRequest(0, 2))
        .getContent()
        .stream()
        .filter(b -> b.getServedTable() != null)
        .findFirst();
    if (first.isPresent()) {
      Bill billSliced = first.get();
      bill.setServedTable(billSliced.getServedTable());
    } else {
      bill.setServedTable(bill.getId());
    }
    billRepository.save(bill);

    return bill.getOrderItems().stream()
        .map(orderItem -> new OrderPrint(orderItem))
        .collect(Collectors.toList());
  }

  private List<OrderItem> orderInsToOrderItems(List<OrderIn> orderIns) {
    if (orderIns == null) {
      return Collections.EMPTY_LIST;
    }
    Long orderNumber = counterService.nextOrderNumber();
    return orderIns.stream()
        .filter(orderIn -> orderIn.getQuantity() != null && orderIn.getQuantity() > 0)
        .map(orderIn -> {
              Dish dish = dishService.findByid(orderIn.getDishId());
              return new OrderItem(dish, orderIn.getQuantity(), orderIn.getComments(), orderNumber);
            }
        ).collect(Collectors.toList());
  }

  @Transactional
  public List<OrderPrint> addOrders(long billId, List<OrderIn> orderIns) {
    if (orderIns.isEmpty()) {
      throw new EmptyOrderListException();
    }
    Bill bill = getBill(billId);
    if (!bill.isOpened()) {
      throw new BillAllreadyClosedException();
    }
    if (!securityService.getEmployeeFromSession().getId().equals(bill.getEmployee().getId())) {
      throw new DeniedException();
    }
    List<OrderItem> orderItems = orderInsToOrderItems(orderIns);
    bill.addOrderItems(orderItems);
    bill = billRepository.save(bill);

    return bill.getOrderItems().stream()
        .map(orderItem -> new OrderPrint(orderItem))
        .collect(Collectors.toList());
  }

  private Bill getBill(long billId) {
    return billRepository.findById(billId).orElseThrow(() -> new BillNotFoundException());
  }

  @Transactional
  public BillPrint print(Long billId, Long discountId) {
    Bill bill = getBill(billId);
    if (bill.isOpened()) {
      if (discountId != null) {
        Discount discount = discountService.findById(discountId);
        if (discount.isActivated()) {
          bill.setDiscount(discount);
          Money totalPrice = bill.getTotalPrice();
          int discountSize = bill.getDiscount().getSize();
          Money discountedSum = totalPrice.multiply(discountSize / 100f);
          Money toPaid = totalPrice.subtract(discountedSum);
          bill.setMoneyDiscounted(discountedSum);
          bill.setMoneyPaid(toPaid);
        }
      } else {
        bill.setDiscount(null);
        Money totalPrice = bill.getTotalPrice();
        bill.setMoneyPaid(totalPrice);
        bill.setMoneyDiscounted(Money.ZERO);
      }
      bill.setPayStatus(AWAIT);
      billRepository.save(bill);
    }
    return new BillPrint(bill);
  }

  public void close(Long billId, PayType payType) {
    Employee employeeFromSession = securityService.getEmployeeFromSession();
    Bill bill = getBill(billId);
    if (!bill.getEmployee().getId().equals(employeeFromSession.getId())) {
      throw new DeniedException();
    }
    bill.setPayType(payType);
    bill.setPayStatus(PAID);
    bill.setOpened(false);
    bill.setCloseBillTime(timeService.getCurrentTime());
    billRepository.save(bill);
  }

  public List<BillPrint> getOpened() {
    Employee employee = securityService.getEmployeeFromSession();
    return billRepository.findOpenedByEmployee(employee)
        .stream()
        .map(item -> new BillPrint(item))
        .collect(Collectors.toList());
  }

  List<Bill> getCurrentShiftBills(Employee employee) {
    return billRepository.findCurrentShiftBillsByEmployee(employee);
  }

  public List<Bill> findBillsByPeriodAndEmployee(LocalDate startDate, LocalDate endDate,
      Employee employee) {
    return billRepository.findBillsByPeriodAndEmployee(
        startDate.atStartOfDay()
        , endDate.atStartOfDay()
        , employee);
  }
}