package cabare.service;

import cabare.data.BillRepository;
import cabare.dto.BillDto;
import cabare.dto.OrderIn;
import cabare.dto.OrderPrint;
import cabare.entity.domain.Money;
import cabare.entity.domain.PayStatus;
import cabare.entity.model.Bill;
import cabare.entity.model.Dish;
import cabare.entity.model.Employee;
import cabare.entity.model.OrderItem;
import cabare.exception.BillAllreadyClosedException;
import cabare.exception.BillNotFoundException;
import cabare.exception.DeniedException;
import cabare.exception.EmptyOrderListException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
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
    bill.setPayStatus(PayStatus.AWAIT);
    bill = billRepository.save(bill);

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
    Bill bill = billRepository.findById(billId).orElseThrow(() -> new BillNotFoundException());
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
}