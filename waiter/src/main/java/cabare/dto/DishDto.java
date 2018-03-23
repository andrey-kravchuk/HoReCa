package cabare.dto;

import cabare.entity.model.Dish;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DishDto {

  @JsonProperty("id")
  public Long id;

  @JsonProperty("name")
  public String name;

  @JsonProperty(value = "photo")
  public String photo;

  @JsonProperty("dish_out")
  public int dishOut;

  @JsonProperty("price")
  public String price;

  @JsonProperty("category_id")
  public Long categoryId;

  @JsonProperty("start_day")
  public Integer startDay;

  @JsonProperty("end_day")
  public Integer endDay;

  @JsonProperty("quantity")
  public Integer quantity;

  public DishDto() {
  }

  public DishDto(Dish dish) {
    this.id = dish.getId();
    this.name = dish.getName();
    this.photo = dish.getPhoto();
    this.dishOut = dish.getDishOut();
    this.price = dish.getPrice().toString();
    this.categoryId = dish.getDishCategory().getId();
    this.startDay = dish.getStartDay();
    this.endDay = dish.getEndDay();
    this.quantity = dish.getQuantity();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public int getDishOut() {
    return dishOut;
  }

  public void setDishOut(int dishOut) {
    this.dishOut = dishOut;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public Integer getStartDay() {
    return startDay;
  }

  public void setStartDay(Integer startDay) {
    this.startDay = startDay;
  }

  public Integer getEndDay() {
    return endDay;
  }

  public void setEndDay(Integer endDay) {
    this.endDay = endDay;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
}
