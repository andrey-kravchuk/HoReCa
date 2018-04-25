package cabare.dto;

import cabare.entity.model.DishCategory;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

public class DishCategoryDto {

  @NotNull(message = "dish_cabare_id should be specified")
  @Min(value = 1, message = "dish_cabare_id cannot be less than 1")
  @JsonProperty("id")
  private Long id;

  @NotBlank(message = "name should be specified")
  @JsonProperty("name")
  private String name;

  @JsonProperty("photo")
  private String photo;

  @NotNull(message = "zone_id should be specified")
  @Min(value = 1, message = "zone_id cannot be less than 1")
  @JsonProperty("zone_id")
  private Long zoneId;

  @NotNull(message = "category_id should be specified")
  @Min(value = 1, message = "category_id cannot be less than 1")
  @JsonProperty("cabare_id")
  private Long cabareId;

  public DishCategoryDto() {
  }

  public DishCategoryDto(DishCategory dishCategory) {
    this.id = dishCategory.getId();
    this.name = dishCategory.getName();
    this.photo = dishCategory.getPhoto();
    this.zoneId = dishCategory.getZone().getId();
    this.cabareId = dishCategory.getCabare().getId();
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

  public Long getZoneId() {
    return zoneId;
  }

  public void setZoneId(Long zoneId) {
    this.zoneId = zoneId;
  }

  public Long getCabareId() { return cabareId; }

  public void setCabareId(Long cabareId) { this.cabareId = cabareId; }
}
