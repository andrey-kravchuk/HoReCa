package cabare.controller;

import cabare.dto.BillDto;
import cabare.dto.BillPrint;
import cabare.dto.OrderIn;
import cabare.dto.OrderPrint;
import cabare.service.BillService;
import cabare.service.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController extends ExceptionHandlerController {

  @Autowired
  private BillService billService;
  @Autowired
  private SecurityService securityService;

  @RequestMapping(value = "/open", method = RequestMethod.PUT)
  public List<OrderPrint> openBill(@RequestBody BillDto billDto, @RequestParam Long employeeId) {
    securityService.authorizeEmployee(employeeId);
    return billService.openBill(billDto);
  }

  @RequestMapping(value = "/add/orderitems", method = RequestMethod.PUT)
  public List<OrderPrint> addOrder(@RequestParam Long billId, @RequestBody List<OrderIn> orderIns,
      @RequestParam Long employeeId) {
    securityService.authorizeEmployee(employeeId);
    return billService.addOrders(billId, orderIns);
  }

  @RequestMapping(value = "/print", method = RequestMethod.POST)
  public BillPrint preCloseBill(@RequestParam(name = "bill_id") Long billId,
      @RequestParam(name = "discount_id") Long discountId) {
    return billService.print(billId, discountId);
  }

  @RequestMapping(value = "/close", method = RequestMethod.POST)
  public void close(@RequestParam(name = "bill_id") Long billId,
      @RequestParam Long employeeId) {
    securityService.authorizeEmployee(employeeId);
    billService.close(billId);
  }
}
