package cabare.service;


import cabare.entity.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

  @Autowired
  private EmployeeService employeeService;

  public Employee getEmployeeFromSession() {
    String email = "vasya@gmail.com";
    return employeeService.findByEmail(email);
  }
}
