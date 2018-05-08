package cabare.dto;

import cabare.entity.model.Ingredient;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IngredientDto {
  @JsonProperty("id")
  private Long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("measure")
  private String measureAbbreviation;

  public IngredientDto() {
  }

  public IngredientDto(Ingredient ingredient) {
    this.id = ingredient.getId();
    this.name = ingredient.getName();
    this.measureAbbreviation = ingredient.getMeasure().getAbbreviation();
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

  public String getMeasureAbbreviation() {
    return measureAbbreviation;
  }

  public void setMeasureAbbreviation(String measureAbbreviation) {
    this.measureAbbreviation = measureAbbreviation;
  }
}
