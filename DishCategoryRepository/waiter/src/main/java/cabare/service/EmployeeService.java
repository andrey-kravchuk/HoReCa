package cabare.service;

import cabare.entity.model.Employee;
import cabare.exception.EmployeeNotFoundException;
import cabare.exception.EmployeeNotSpecifiedException;
import cabare.data.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  public Employee getById(Long employeeId) {
    if (employeeId == null) {
      throw new EmployeeNotSpecifiedException();
    }
    return employeeRepository.findById(employeeId)
        .orElseThrow(() -> new EmployeeNotFoundException());
  }
}
