package cabare.dto;

import cabare.entity.domain.SaleType;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BillDto {

  @NotNull(message = "table_number should be specified")
  @JsonProperty("table_number")
  private Integer tableNumber;

  @NotNull(message = "number_of_persons should be specified")
  @Min(value = 1, message = "number_of_persons cannot be less than 1")
  @JsonProperty("number_of_persons")
  private Integer numberOfPersons;

  @JsonProperty("sale_type")
  private SaleType saleType;

  @Valid
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
