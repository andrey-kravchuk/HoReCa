package cabare.dto;

import cabare.entity.domain.Money;
import cabare.entity.model.BillWaiter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BillPrint {

  @JsonProperty("orders")
  private List<OrderPrint> orders = new ArrayList<>();

  @JsonProperty(value = "total_sum", defaultValue = "0")
  private String totalSum;

  @JsonProperty(value = "discounted_sum", defaultValue = "0")
  private String discountedSum;

  @JsonProperty(value = "to_paid_sum", defaultValue = "0")
  private String toPaid;

  @JsonProperty("discount_size")
  private int discountSize;

  @JsonProperty("bill_id")
  private String billId;

  @JsonProperty(value = "open_bill_date")
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
  private LocalDateTime openBillDate;

  @JsonProperty(value = "close_bill_date")
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
  private LocalDateTime closeBillDate;

  @JsonProperty("employee_name")
  private String employeeName;

  @JsonProperty("table_number")
  private Integer tableNumber;

  @JsonIgnore
  private Money sum = Money.ZERO;


  public BillPrint(BillWaiter billWaiter) {
    billWaiter.getOrderItems().forEach(orderItem -> {
      orders.add(new OrderPrint(orderItem));
      this.sum = orderItem.getTotalPrice().add(sum);
    });
    this.totalSum = sum.getValue();
    this.toPaid = billWaiter.getMoneyPaid().getValue();
    this.discountedSum = billWaiter.getMoneyDiscounted().getValue();
    if (billWaiter.getDiscount() != null) {
      this.discountSize = billWaiter.getDiscount().getSize();
    }
    this.billId = billWaiter.getId().toString();
    this.closeBillDate = billWaiter.getCloseBillTime();
    this.openBillDate = billWaiter.getOpenBillTime();
    this.employeeName = billWaiter.getEmployee().getName();
    this.tableNumber = billWaiter.getTableNumber();
  }

  public String getToPaid() {
    return toPaid;
  }

  public void setOrders(List<OrderPrint> orders) {
    this.orders = orders;
  }

  public void setTotalSum(String totalSum) {
    this.totalSum = totalSum;
  }

  public void setDiscountedSum(String discountedSum) {
    this.discountedSum = discountedSum;
  }

  public void setDiscountSize(int discountSize) {
    this.discountSize = discountSize;
  }

  public void setBillId(String billId) {
    this.billId = billId;
  }

  public void setOpenBillDate(LocalDateTime openBillDate) {
    this.openBillDate = openBillDate;
  }

  public void setCloseBillDate(LocalDateTime closeBillDate) {
    this.closeBillDate = closeBillDate;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public void setSum(Money sum) {
    this.sum = sum;
  }

  public void setToPaid(String toPaid) {
    this.toPaid = toPaid;
  }

  public void setTableNumber(Integer tableNumber) {
    this.tableNumber = tableNumber;
  }
}

