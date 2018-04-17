package cabare.dto;

import cabare.entity.model.Zone;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ZoneDto {

    @JsonProperty("id")
    public Long id;

    @JsonProperty("name")
    public String name;

    public ZoneDto(){
    }

    public ZoneDto(Zone zone){
        this.id = zone.getId();
        this.name = zone.getName();
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
}
