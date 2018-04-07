package cabare.controller;

import cabare.dto.BillDto;
import cabare.dto.BillPrint;
import cabare.dto.OrderIn;
import cabare.dto.OrderPrint;
import cabare.entity.domain.PayType;
import cabare.service.BillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/waiter/bill")
public class BillController {

  @Autowired
  private BillService billService;

  @RequestMapping(value = "/open", method = RequestMethod.PUT)
  public List<OrderPrint> openBill(@RequestBody BillDto billDto) {
    return billService.openBill(billDto);
  }

  @RequestMapping(value = "/add/orderitems", method = RequestMethod.POST)
  public List<OrderPrint> addOrder(@RequestParam(name = "bill_id") Long billId
      , @RequestBody List<OrderIn> orderIns) {
    return billService.addOrders(billId, orderIns);
  }

  @RequestMapping(value = "/print", method = RequestMethod.POST)
  public BillPrint preCloseBill(@RequestParam(name = "bill_id") Long billId
      , @RequestParam(name = "discount_id") Long discountId) {
    return billService.print(billId, discountId);
  }

  @RequestMapping(value = "/close", method = RequestMethod.POST)
  public void close(@RequestParam(name = "bill_id") Long billId
      , @RequestParam(name = "pay_type") PayType payType) {
    billService.close(billId, payType);
  }

  @RequestMapping(value = "/opened")
  public List<BillPrint> getOpened() {
    return billService.getOpened();
  }
}
