package cabare.dto;

import cabare.entity.model.Employee;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WaiterDto {

  @JsonProperty("id")
  private Long id;
  @JsonProperty("name")
  private String name;

  public WaiterDto() {
  }

  public WaiterDto(Employee employee) {
    this.id = employee.getId();
    this.name = employee.getName();
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
