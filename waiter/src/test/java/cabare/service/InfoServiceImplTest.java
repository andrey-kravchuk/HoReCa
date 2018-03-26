package cabare.service;

import static cabare.entity.domain.PayType.CASH;
import static cabare.entity.domain.PayType.CASHLESS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import cabare.dto.WaiterStatistic;
import cabare.entity.domain.Money;
import cabare.entity.domain.PayType;
import cabare.entity.model.Bill;
import cabare.entity.model.Employee;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InfoServiceImplTest {

  @InjectMocks
  private InfoServiceImpl infoService;
  @Mock
  private BillService billService;
  @Mock
  private EmployeeService employeeService;

  @Before
  public void init() {
    List<Bill> bills = Arrays.asList(
        buildBill(true, new Money("50.00"), CASH),
        buildBill(false, new Money("50.00"), CASH),
        buildBill(false, new Money("20.00"), CASH),
        buildBill(false, new Money("10.00"), CASHLESS),
        buildBill(false, new Money("100.00"), CASHLESS)
    );

    when(billService.getCurrentShiftBills(any())).thenReturn(bills);
    when(employeeService.getById(any())).thenReturn(new Employee());
  }

  @Test
  public void getCurrentInfo() throws Exception {
    WaiterStatistic waiterStatistic = infoService.getCurrentInfo(1L);
    assertThat(waiterStatistic.getOpenedBillCount()).isEqualTo(1);
    assertThat(waiterStatistic.getOpenedBillSum()).isEqualTo("50.00");

    assertThat(waiterStatistic.getClosedBillCount()).isEqualTo(4);
    assertThat(waiterStatistic.getClosedCashlessSum()).isEqualTo("110.00");
    assertThat(waiterStatistic.getClosedCashSum()).isEqualTo("70.00");
    assertThat(waiterStatistic.getClosedTotalSum()).isEqualTo("180.00");
    assertThat(waiterStatistic.getAverageCheckSum()).isEqualTo("45.00");
    assertThat(waiterStatistic.getMinCheck()).isEqualTo("10.00");
    assertThat(waiterStatistic.getMaxCheck()).isEqualTo("100.00");
    assertThat(waiterStatistic.getVisitorsServed()).isEqualTo(4);
    assertThat(waiterStatistic.getServedTables()).isEqualTo(1);
  }

  private Bill buildBill(Boolean isOpened, Money moneyPaid, PayType payType) {
    Bill bill = spy(new Bill());
    when(bill.getTotalPrice()).thenReturn(new Money("50.00"));
    bill.setOpened(isOpened);
    bill.setMoneyPaid(moneyPaid);
    bill.setPayType(payType);
    bill.setNumberOfPersons(1);
    return bill;
  }

}