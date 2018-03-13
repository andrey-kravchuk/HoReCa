package cabare.service;

import cabare.entity.model.Employee;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
public class EmployeeForeignService {


  private Employee employee;

  public Employee getEmployee() {
    return employee;
  }
}
