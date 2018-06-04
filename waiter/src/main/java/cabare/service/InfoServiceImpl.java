package cabare.service;

import cabare.dto.WaiterStatistic;
import cabare.entity.domain.Money;
import cabare.entity.domain.PayType;
import cabare.entity.model.BillWaiter;
import cabare.entity.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
class InfoServiceImpl implements InfoService {

  @Autowired
  private BillService billService;
  @Autowired
  private SecurityService securityService;

  @Override
  public WaiterStatistic getCurrentInfo() {
    Employee employee = securityService.getEmployeeFromSession();
    List<BillWaiter> bills = billService.getCurrentShiftBills(employee);
    return createReport(bills);
  }

  @Override
  public WaiterStatistic getInfoForPeriod(LocalDate startDate, LocalDate endDate) {
    Employee employee = securityService.getEmployeeFromSession();
    List<BillWaiter> bills = billService.findBillsByPeriodAndEmployee(startDate, endDate, employee);
    return createReport(bills);
  }

  private WaiterStatistic createReport(List<BillWaiter> bills) {
    int openedBillCount = 0;
    int closedBillCount = 0;
    Money openedBillSum = Money.ZERO;
    Money closedCashSum = Money.ZERO;
    Money closedCashlessSum = Money.ZERO;
    Money minCheck = Money.ZERO;
    Money maxCheck = Money.ZERO;
    int servedTables = 0;
    int visitorsServed = 0;
    Set<Long> set = new HashSet<>();

    for (BillWaiter billWaiter : bills) {
      Money moneyPaid;
      if (billWaiter.isOpened()) {
        openedBillCount++;
        moneyPaid = billWaiter.getTotalPrice();
        openedBillSum = openedBillSum.add(moneyPaid);
      } else {
        moneyPaid = billWaiter.getMoneyPaid();
        if (minCheck == Money.ZERO) {
          minCheck = moneyPaid;
        }
        minCheck = getMin(minCheck, moneyPaid);
        maxCheck = getMax(maxCheck, moneyPaid);
        closedBillCount++;
        visitorsServed += billWaiter.getNumberOfPersons();
        set.add(billWaiter.getServedTable());
        if (billWaiter.getPayType() == PayType.CASH) {
          closedCashSum = closedCashSum.add(moneyPaid);
        } else {
          closedCashlessSum = closedCashlessSum.add(moneyPaid);
        }
      }
    }
    servedTables = set.size();
    Money closedTotalSum = closedCashlessSum.add(closedCashSum);
    Money averageCheckSum = closedBillCount > 0
        ? closedTotalSum.divide(closedBillCount)
        : Money.ZERO;

    WaiterStatistic waiterStatistic = new WaiterStatistic();
    waiterStatistic.setClosedBillCount(closedBillCount);
    waiterStatistic.setOpenedBillCount(openedBillCount);
    waiterStatistic.setOpenedBillSum(openedBillSum);
    waiterStatistic.setClosedCashSum(closedCashSum);
    waiterStatistic.setClosedCashlessSum(closedCashlessSum);
    waiterStatistic.setClosedTotalSum(closedTotalSum);
    waiterStatistic.setAverageCheckSum(averageCheckSum);
    waiterStatistic.setMinCheck(minCheck);
    waiterStatistic.setMaxCheck(maxCheck);
    waiterStatistic.setServedTables(servedTables);
    waiterStatistic.setVisitorsServed(visitorsServed);
    return waiterStatistic;
  }

  private Money getMax(Money maxCheck, Money moneyPaid) {
    return maxCheck.isMoreThan(moneyPaid) ? maxCheck : moneyPaid;
  }

  private Money getMin(Money minCheck, Money moneyPaid) {
    return minCheck.isLessThan(moneyPaid) ? minCheck : moneyPaid;
  }
}
