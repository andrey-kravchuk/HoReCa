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
@Table(name = "ingredient")
public class Ingredient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", unique = true)
  private String name;

  @ManyToOne
  @JoinColumn(name = "measure_id")
  private Measure measure;

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
    if (!(o instanceof Ingredient)) {
      return false;
    }
    Ingredient that = (Ingredient) o;
    return Objects.equals(name, that.name) &&
        Objects.equals(cabare, that.cabare);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name, cabare);
  }

  @Override
  public String toString() {
    return "Ingredient{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", measure=" + measure +
        ", cabare=" + cabare +
        '}';
  }
}
