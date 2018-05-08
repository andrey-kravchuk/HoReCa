package cabare.dto;

import cabare.entity.model.Measure;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MeasureDto {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("abbreviation")
  private String abbreviation;

  public MeasureDto() {
  }

  public MeasureDto(Measure measure) {
    this.id = measure.getId();
    this.name = measure.getName();
    this.abbreviation = measure.getAbbreviation();
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

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }
}
