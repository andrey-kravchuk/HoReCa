package cabare.dto;

import cabare.entity.model.DishWaiter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DishDto {

  @NotNull(message = "dish_id should be specified")
  @Min(value = 1, message = "dish_id cannot be less than 1")
  @JsonProperty("id")
  public Long id;

  @NotBlank(message = "name should be specified")
  @JsonProperty("name")
  public String name;

  @JsonProperty(value = "photo")
  public String photo;

  @Min(value = 0, message = "dish_out cannot be less than 0")
  @JsonProperty("dish_out")
  public Integer dishOut;

  @NotBlank
  @JsonProperty("price")
  public String price;

  @NotNull(message = "category_id should be specified")
  @Min(value = 1, message = "category_id cannot be less than 1")
  @JsonProperty("category_id")
  public Long categoryId;

  @Range(min = 0, max = 366, message = "start_day can be between 0 and 366")
  @JsonProperty("start_day")
  public Integer startDay;

  @Range(min = 0, max = 366, message = "end_day can be between 0 and 366")
  @JsonProperty("end_day")
  public Integer endDay;

  @Min(value = 0, message = "quantity cannot be less than 0")
  @JsonProperty("quantity")
  public Integer quantity;

  public DishDto() {
  }

  public DishDto(DishWaiter dishWaiter) {
    this.id = dishWaiter.getId();
    this.name = dishWaiter.getName();
    this.photo = dishWaiter.getPhoto();
    this.dishOut = dishWaiter.getDishOut();
    this.price = dishWaiter.getPrice().toString();
    this.categoryId = dishWaiter.getDishCategory().getId();
    this.startDay = dishWaiter.getStartDay();
    this.endDay = dishWaiter.getEndDay();
    this.quantity = dishWaiter.getQuantity();
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

  public Integer getDishOut() {
    return dishOut;
  }

  public void setDishOut(Integer dishOut) {
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


