package cabare.entity.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "measure")
public class Measure {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", unique = true)
  private String name;

  @Column(name = "abbreviation")
  private String abbreviation;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Measure)) {
      return false;
    }
    Measure measure = (Measure) o;
    return Objects.equals(name, measure.name) &&
        Objects.equals(abbreviation, measure.abbreviation);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name, abbreviation);
  }

  @Override
  public String toString() {
    return "Measure{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", abbreviation='" + abbreviation + '\'' +
        '}';
  }
}
