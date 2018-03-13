package cabare.service;

import cabare.entity.model.Employee;
import cabare.exception.EmployeeNotFoundException;
import cabare.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  public Employee getById(Long employeeId) {
    return employeeRepository.findById(employeeId)
        .orElseThrow(() -> new EmployeeNotFoundException());
  }
}
