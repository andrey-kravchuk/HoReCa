package cabare.dto;

import cabare.entity.domain.SaleType;
import cabare.entity.model.Bill;
import cabare.entity.model.OrderItem;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BillDto {

  @JsonProperty("table_number")
  private Integer tableNumber;

  @JsonProperty("number_of_persons")
  private Integer numberOfPersons;

  @JsonProperty("sale_type")
  private SaleType saleType;

  @JsonProperty("order_items")
  private List<OrderIn> orderIns;


  public Integer getTableNumber() {
    return tableNumber;
  }

  public Integer getNumberOfPersons() {
    return numberOfPersons;
  }

  public SaleType getSaleType() {
    return saleType;
  }

  public List<OrderIn> getOrderIns() {
    return orderIns;
  }
}
