package cabare.service;

import cabare.dto.WaiterStatistic;

import java.time.LocalDate;

public interface InfoService {

  WaiterStatistic getCurrentInfo(Long employeeId);

  WaiterStatistic getInfoForPeriod(LocalDate startDate, LocalDate endDate, Long employeeId);
}
