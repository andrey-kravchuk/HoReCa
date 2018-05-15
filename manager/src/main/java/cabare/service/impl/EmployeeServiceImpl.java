package cabare.service.impl;


import cabare.dto.EmployeeDto;
import cabare.entity.model.Employee;
import cabare.exceptions.EmployeeNotFoundException;
import cabare.exceptions.EmployeeNotSpecifiedException;
import cabare.repository.EmployeeRepository;
import cabare.service.EmployeeService;
import cabare.service.SecurityService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private SecurityService securityService;

  @Override
  public EmployeeDto getById(Long employeeId) {
    if (employeeId == null) {
      throw new EmployeeNotSpecifiedException();
    }
    return new EmployeeDto(employeeRepository.findById(employeeId)
        .orElseThrow(() -> new EmployeeNotFoundException()));
  }

  @Override
  public Employee findByEmail(String email) {
    return employeeRepository.findByEmail(email).orElseThrow(() -> new EmployeeNotFoundException());
  }

  @Override
  public List<EmployeeDto> getAllWaiters() {
    Employee manager = securityService.getEmployeeFromSession();
    return employeeRepository.getAllByCabare(manager.getCabare()).stream()
        .map(employee -> new EmployeeDto(employee))
        .collect(Collectors.toList());
  }

  @Override
  public List<EmployeeDto> getAllEnabledWaiters() {
    Employee manager = securityService.getEmployeeFromSession();
    return employeeRepository.getAllByEnable(manager.getCabare()).stream()
        .map(employee -> new EmployeeDto(employee))
        .collect(Collectors.toList());
  }

  @Override
  public void enable(Long employeeId) {
    if (employeeId == null) {
      throw new EmployeeNotSpecifiedException();
    }
    Employee employee = employeeRepository.findById(employeeId).get();
    employee.setEnabled(true);
    employeeRepository.save(employee);
  }

  @Override
  public void disable(Long employeeId) {
    if (employeeId == null) {
      throw new EmployeeNotSpecifiedException();
    }
    Employee employee = employeeRepository.findById(employeeId).get();
    employee.setEnabled(false);
    employeeRepository.save(employee);
  }
}

