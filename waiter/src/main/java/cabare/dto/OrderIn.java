package cabare.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OrderIn {

  @NotNull(message = "dish_id should be specified")
  @Min(value = 1, message = "dish_id cannot be less than 1")
  @JsonProperty("dish_id")
  private Long dishId;

  @NotNull(message = "quantity should be specified")
  @Min(value = 1, message = "quantity cannot be less than 1")
  @JsonProperty("quantity")
  private Integer quantity;

  @JsonProperty("comments")
  private String comments;


  public Long getDishId() {
    return dishId;
  }

  public void setDishId(Long dishId) {
    this.dishId = dishId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }
}
