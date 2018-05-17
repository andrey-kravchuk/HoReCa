package cabare.dto;

import cabare.entity.model.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeeDto {

  @JsonProperty("id")
  Long id;

  @JsonProperty("name")
  String name;

  @JsonProperty("enabled")
  boolean enabled;

  @JsonProperty("email")
  String email;

  @JsonProperty("cabare")
  String cabare;

  public EmployeeDto() {
  }

  public EmployeeDto(Employee employee) {
    this.id = employee.getId();
    this.name = employee.getName();
    this.enabled = employee.getEnabled();
    this.email = employee.getEmail();
    this.cabare = employee.getCabare().getName();
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

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCabare() {
    return cabare;
  }

  public void setCabare(String cabare) {
    this.cabare = cabare;
  }
}
