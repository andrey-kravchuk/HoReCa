package cabare.service;

import cabare.dto.CurrentInfo;
import cabare.entity.model.Employee;

public interface InfoService {

  CurrentInfo getCurrentInfo(Long employeeId);
}
