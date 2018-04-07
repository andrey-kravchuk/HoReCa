package cabare.service;

import cabare.dto.WaiterStatistic;

import java.time.LocalDate;

public interface InfoService {

  WaiterStatistic getCurrentInfo();

  WaiterStatistic getInfoForPeriod(LocalDate startDate, LocalDate endDate);
}
