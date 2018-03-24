package cabare.controller;


import cabare.dto.CurrentInfo;
import cabare.service.InfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/report")
public class ReportController {

  @Autowired
  private InfoService infoService;

  @RequestMapping(value = "/quick_report", method = RequestMethod.POST)
  public CurrentInfo quickReport(@RequestParam(name = "employee_id") Long employeeId) {
    return infoService.getCurrentInfo(employeeId);
  }
}
