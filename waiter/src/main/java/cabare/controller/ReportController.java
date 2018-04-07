package cabare.controller;


import cabare.dto.WaiterStatistic;
import cabare.service.InfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/waiter/report")
public class ReportController {

  @Autowired
  private InfoService infoService;

  @RequestMapping(value = "/quick_report")
  public WaiterStatistic quickReport(@RequestParam(name = "employee_id") Long employeeId) {
    return infoService.getCurrentInfo(employeeId);
  }

  @RequestMapping(value = "/by_date")
  public WaiterStatistic getReport(
      @RequestParam(name = "start_date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate
      , @RequestParam(name = "end_date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate
      , @RequestParam(name = "employee_id") Long employeeId) {
    return infoService.getInfoForPeriod(startDate, endDate, employeeId);
  }
}
