package cabare.service;

import cabare.dto.EmployeeDto;
import cabare.entity.model.Employee;
import java.util.List;

public interface EmployeeService {

  EmployeeDto getById(Long employeeId);

  Employee findByEmail(String email);

  List<EmployeeDto> getAllWaiters();

  List<EmployeeDto> getAllEnabledWaiters();

  void enable(Long employeeId);

  void disable(Long employeeId);

}
