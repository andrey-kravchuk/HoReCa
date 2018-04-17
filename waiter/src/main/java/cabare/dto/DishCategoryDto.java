package cabare.dto;

import cabare.entity.model.DishCategory;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DishCategoryDto {

    @JsonProperty("id")
    public Long id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("photo")
    public String photo;

    @JsonProperty("zone_id")
    public Long zoneId;

    public DishCategoryDto() {
    }

    public DishCategoryDto(DishCategory dishCategory) {
        this.id = dishCategory.getId();
        this.name = dishCategory.getName();
        this.photo = dishCategory.getPhoto();
        this.zoneId = dishCategory.getZone().getId();

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
}
