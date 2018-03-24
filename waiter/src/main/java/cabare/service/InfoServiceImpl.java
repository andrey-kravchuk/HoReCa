package cabare.service;

import cabare.dto.CurrentInfo;
import cabare.entity.domain.Money;
import cabare.entity.domain.PayType;
import cabare.entity.model.Bill;
import cabare.entity.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class InfoServiceImpl implements InfoService {

  @Autowired
  private BillService billService;
  @Autowired
  private EmployeeService employeeService;

  @Override
  public CurrentInfo getCurrentInfo(Long employeeId) {
    Employee employee = employeeService.getById(employeeId);
    List<Bill> bills = billService.getCurrentShiftBills(employee);

    int openedBillCount = 0;
    int closedBillCount = 0;
    Money openedBillSum = Money.ZERO;
    Money closedCashSum = Money.ZERO;
    Money closedCashlessSum = Money.ZERO;

    for (Bill bill : bills) {
      Money moneyPaid = bill.getMoneyPaid();
      if (bill.isOpened()) {
        openedBillCount++;
        moneyPaid = moneyPaid.add(bill.getTotalPrice());
        openedBillSum = openedBillSum.add(moneyPaid);
      } else {
        closedBillCount++;
        if (bill.getPayType() == PayType.CASH) {
          closedCashSum = closedCashSum.add(moneyPaid);
        } else {
          closedCashlessSum = closedCashlessSum.add(moneyPaid);
        }
      }
    }
    Money closedTotalSum = closedCashlessSum.add(closedCashSum);
    Money averageCheckSum =
        closedBillCount > 0 ? closedTotalSum.divide(closedBillCount) : Money.ZERO;

    CurrentInfo currentInfo = new CurrentInfo();
    currentInfo.setClosedBillCount(closedBillCount);
    currentInfo.setOpenedBillCount(openedBillCount);
    currentInfo.setOpenedBillSum(openedBillSum);
    currentInfo.setClosedCashSum(closedCashSum);
    currentInfo.setClosedCashlessSum(closedCashlessSum);
    currentInfo.setClosedTotalSum(closedTotalSum);
    currentInfo.setAverageCheckSum(averageCheckSum);

    return currentInfo;
  }
}
