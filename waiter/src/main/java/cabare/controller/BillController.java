package cabare.controller;

import cabare.dto.BillDto;
import cabare.dto.OrderPrint;
import cabare.service.BillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

  @Autowired
  private BillService billService;

  @RequestMapping(value = "/open", method = RequestMethod.PUT)
  public List<OrderPrint> openBill(@RequestBody BillDto billDto, @RequestParam Long employeeId) {
    return billService.openBill(billDto, employeeId);
  }
}
