package cabare.dto;

import cabare.entity.model.Calculation;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class CalculationDto {

  @JsonProperty(value = "id")
  private Long id;

  @JsonProperty(value = "number")
  private Long number;

  @JsonProperty(value = "date")
  private LocalDate date;

  @JsonProperty(value = "dish_id")
  private Long dishId;

  @JsonProperty(value = "ingredient_id")
  private Long ingredientId;

  @JsonProperty(value = "quantity")
  private Double quantity;

  public CalculationDto() {
  }

  public CalculationDto(Calculation calculation) {
    this.id = calculation.getId();
    this.number = calculation.getNumber();
    this.date = calculation.getDate();
    this.dishId = calculation.getDish().getId();
    this.ingredientId = calculation.getIngredient().getId();
    this.quantity = calculation.getQuantity();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getNumber() {
    return number;
  }

  public void setNumber(Long number) {
    this.number = number;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Long getDishId() {
    return dishId;
  }

  public void setDishId(Long dishId) {
    this.dishId = dishId;
  }

  public Long getIngredientId() {
    return ingredientId;
  }

  public void setIngredientId(Long ingredientId) {
    this.ingredientId = ingredientId;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }
}
