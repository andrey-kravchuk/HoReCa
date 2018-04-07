package cabare.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import javax.validation.Valid;

public class OrderList {

  @Valid
  @JsonProperty("order_items")
  private List<OrderIn> orderIns;

  public List<OrderIn> getOrderIns() {
    return orderIns;
  }

  public void setOrderIns(List<OrderIn> orderIns) {
    this.orderIns = orderIns;
  }
}
