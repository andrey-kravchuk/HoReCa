package cabare.dto;

import cabare.entity.model.Dish;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class DishDto {

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

  @JsonProperty("quantity")
  public Integer quantity = -1;

  @JsonProperty("isArchived")
  private Boolean isArchived = false;

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
    this.isArchived = dish.getArchived();
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

  public Boolean getArchived() {
    return isArchived;
  }

  public void setArchived(Boolean archived) {
    isArchived = archived;
  }
}


