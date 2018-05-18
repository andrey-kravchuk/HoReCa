package cabare.dto;

import cabare.entity.model.Calculation;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CalculationDto {

  @NotNull(message = "ingredient_id should be specified")
  @Min(value = 1, message = "ingredient_id should be positive")
  @JsonProperty(value = "ingredient_id")
  private Long ingredientId;

  @JsonProperty(value = "ingredient_name")
  private String ingredientName;

  @NotNull(message = "quantity should be specified")
  @Min(value = 0, message = "quantity should be positive")
  @JsonProperty(value = "quantity")
  private Double quantity;

  @JsonProperty(value = "measure_abbreviation")
  private String measureAbbreviation;


  public CalculationDto() {
  }

  public CalculationDto(Calculation calculation) {
    this.ingredientId = calculation.getIngredient().getId();
    this.ingredientName = calculation.getIngredient().getName();
    this.quantity = calculation.getQuantity();
    this.measureAbbreviation = calculation.getIngredient().getMeasure().getAbbreviation();
  }

  public Long getIngredientId() {
    return ingredientId;
  }

  public void setIngredientId(Long ingredientId) {
    this.ingredientId = ingredientId;
  }

  public String getIngredientName() {
    return ingredientName;
  }

  public void setIngredientName(String ingredientName) {
    this.ingredientName = ingredientName;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public String getMeasureAbbreviation() {
    return measureAbbreviation;
  }

  public void setMeasureAbbreviation(String measureAbbreviation) {
    this.measureAbbreviation = measureAbbreviation;
  }
}
