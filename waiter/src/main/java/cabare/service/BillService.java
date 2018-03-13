package cabare.service;

import cabare.dto.BillDto;
import cabare.dto.OrderIn;
import cabare.dto.OrderPrint;
import cabare.entity.domain.Money;
import cabare.entity.domain.PayStatus;
import cabare.entity.model.Bill;
import cabare.entity.model.Employee;
import cabare.entity.model.OrderItem;
import cabare.repository.BillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {

  @Autowired
  private EmployeeForeignService employeeForeignService;
  @Autowired
  private TimeService timeService;
  @Autowired
  private BillRepository billRepository;

  @Transactional
  public List<OrderPrint> openBill(BillDto billDto, Long employeeId) {
    Employee employee = employeeForeignService.getEmployee();
    List<OrderItem> orderItems = orderInsToOrderItems(billDto.getOrderIns());
    LocalDateTime currentTime = timeService.getCurrentTime();

    Bill bill = new Bill();
    bill.setNumberOfPersons(billDto.getNumberOfPersons());
    bill.setTableNumber(billDto.getTableNumber());
    bill.setSaleType(billDto.getSaleType());
    bill.setOrderItems(orderItems);

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
    return null;
  }
}
