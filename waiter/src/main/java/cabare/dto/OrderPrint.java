package cabare.dto;

import cabare.entity.model.OrderItem;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderPrint {

  @JsonProperty("category")
  private String categoryName;

  @JsonProperty("dish_name")
  private String dishName;

  @JsonProperty("quantity")
  private Integer quantity;

  @JsonProperty("total_price")
  private String totalPrice;

  @JsonProperty("order_number")
  private Long orderNumber;

  @JsonProperty("comments")
  private String comments;

  public OrderPrint(OrderItem orderItem) {
    this.dishName = orderItem.getDishWaiter().getName();
    this.quantity = orderItem.getQuantity();
    this.totalPrice = orderItem.getTotalPrice().getValue();
    this.orderNumber = orderItem.getOrderNumber();
    this.comments = orderItem.getComments();
    this.categoryName = orderItem.getDishWaiter().getDishCategoryWaiter().getName();
  }

  public String getCategoryName() {
    return categoryName;
  }

  public String getDishName() {
    return dishName;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public String getTotalPrice() {
    return totalPrice;
  }

  public Long getOrderNumber() {
    return orderNumber;
  }

  public String getComments() {
    return comments;
  }
}
