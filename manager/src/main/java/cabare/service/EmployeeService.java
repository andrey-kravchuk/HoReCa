package cabare.service;

import cabare.dto.EmployeeDto;
import cabare.entity.model.Employee;
import cabare.exceptions.EmployeeNotFoundException;
import cabare.exceptions.EmployeeNotSpecifiedException;
import cabare.repository.EmployeeRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private SecurityService securityService;

  public EmployeeDto getById(Long employeeId) {
    if (employeeId == null) {
      throw new EmployeeNotSpecifiedException();
    }
    return new EmployeeDto(employeeRepository.findById(employeeId)
        .orElseThrow(() -> new EmployeeNotFoundException()));
  }

  public Employee findByEmail(String email) {
    return employeeRepository.findByEmail(email).orElseThrow(() -> new EmployeeNotFoundException());
  }

  public List<EmployeeDto> getAllWaiters() {
    Employee manager = securityService.getEmployeeFromSession();
    return employeeRepository.getAllByCabare(manager.getCabare()).stream()
        .map(employee -> new EmployeeDto(employee))
        .collect(Collectors.toList());
  }

  public List<EmployeeDto> getAllEnabledWaiters() {
    Employee manager = securityService.getEmployeeFromSession();
    return employeeRepository.getAllByCabare(manager.getCabare()).stream()
        .filter(employee -> employee.getEnabled() == true)
        .map(employee -> new EmployeeDto(employee))
        .collect(Collectors.toList());
  }

  public void enabledOn(Long employeeId) {
    if (employeeId == null) {
      throw new EmployeeNotSpecifiedException();
    }
    Employee employee = employeeRepository.findById(employeeId).get();
    employee.setEnabled(true);
    employeeRepository.save(employee);
  }

  public void enabledOff(Long employeeId) {
    if (employeeId == null) {
      throw new EmployeeNotSpecifiedException();
    }
    Employee employee = employeeRepository.findById(employeeId).get();
    employee.setEnabled(false);
    employeeRepository.save(employee);
  }
}

