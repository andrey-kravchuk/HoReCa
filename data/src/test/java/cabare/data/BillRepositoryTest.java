package cabare.data;

import static org.assertj.core.api.Assertions.assertThat;

import cabare.entity.model.Bill;
import cabare.entity.model.Employee;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BillRepositoryTest {

  @Autowired
  private BillRepository billRepository;
  @Autowired
  private EmployeeRepository employeeRepository;
  private Bill bill;
  private Employee employee;
  private final String employeeName = "John";

  @Before
  public void init() {
    employee = new Employee();
    employee.setName(employeeName);
    Employee savedEmployee = employeeRepository.save(employee);
    bill = new Bill();
    bill.setEmployee(savedEmployee);
  }

//  @Test
//  public void findById() throws Exception {
//    Bill saved = billRepository.save(bill);
//    Long id = saved.getId();
//    Optional<Bill> result = billRepository.findById(id);
//    assertThat(result.isPresent()).isTrue();
//  }

  @Test
  public void findOpenedByEmployee() throws Exception {
    bill.setOpened(true);
    billRepository.save(bill);
    List<Bill> openedByEmployee = billRepository.findOpenedByEmployee(employee);
    assertThat(openedByEmployee.size()).isEqualTo(1);
  }

  @Test
  public void findCurrentShiftBillsByEmployee() throws Exception {
    bill.setActiveShift(true);
    billRepository.save(bill);
    List<Bill> activeShiftBill = billRepository.findCurrentShiftBillsByEmployee(employee);
    assertThat(activeShiftBill.size()).isEqualTo(1);
  }

}