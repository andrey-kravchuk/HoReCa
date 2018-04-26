package cabare.entity.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "unit_size")
public class UnitSize {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", unique = true)
  private String name;

  @Column(name = "abbreviation")
  private String abbreviation;

  @ManyToOne
  @JoinColumn(name = "cabare_id")
  private Cabare cabare;

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

  public Cabare getCabare() {
    return cabare;
  }

  public void setCabare(Cabare cabare) {
    this.cabare = cabare;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UnitSize)) {
      return false;
    }
    UnitSize unitSize = (UnitSize) o;
    return Objects.equals(name, unitSize.name) &&
        Objects.equals(cabare, unitSize.cabare);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name, cabare);
  }

  @Override
  public String toString() {
    return "UnitSize{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", abbreviation='" + abbreviation + '\'' +
        ", cabare=" + cabare +
        '}';
  }
}
