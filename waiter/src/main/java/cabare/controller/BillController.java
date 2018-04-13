package cabare.controller;

import cabare.dto.BillDto;
import cabare.dto.BillPrint;
import cabare.dto.OrderList;
import cabare.dto.OrderPrint;
import cabare.entity.domain.PayType;
import cabare.service.BillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/waiter/bill")
public class BillController {

  @Autowired
  private BillService billService;

  @RequestMapping(value = "/open", method = RequestMethod.PUT)
  public List<OrderPrint> openBill(@RequestBody @Valid BillDto billDto) {
    return billService.openBill(billDto);
  }

  @RequestMapping(value = "/add/orderitems", method = RequestMethod.POST)
  public List<OrderPrint> addOrder(
      @NotNull(message = "bill_id size should be specified")
      @Min(value = 1, message = "bill_id cannot be less than 1")
      @RequestParam(name = "bill_id") Long billId
      , @RequestBody @Valid OrderList orderList) {
    return billService.addOrders(billId, orderList.getOrderIns());
  }

  @RequestMapping(value = "/print", method = RequestMethod.POST)
  public BillPrint preCloseBill(
      @NotNull(message = "bill_id size should be specified")
      @Min(value = 1, message = "bill_id cannot be less than 1")
      @RequestParam(name = "bill_id") Long billId
      ,
      @Min(value = 1, message = "discount_id cannot be less than 1")
      @RequestParam(name = "discount_id") Long discountId) {
    return billService.print(billId, discountId);
  }

  @RequestMapping(value = "/close", method = RequestMethod.POST)
  public void close(
      @NotNull(message = "bill_id size should be specified")
      @Min(value = 1, message = "bill_id cannot be less than 1")
      @RequestParam(name = "bill_id") Long billId
      ,
      @NotNull(message = "pay type should be specified")
      @RequestParam(name = "pay_type") PayType payType) {
    billService.close(billId, payType);
  }

  @RequestMapping(value = "/opened")
  public List<BillPrint> getOpened() {
    return billService.getOpened();
  }
}
