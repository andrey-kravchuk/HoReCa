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
@Table(name = "dish_category")
public class DishCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", unique = true)
  private String name;

  @Column(name = "photo")
  private String photo;

  @ManyToOne
  @JoinColumn(name = "zone_id", nullable = false)
  private Zone zone;

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

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public Zone getZone() {
    return zone;
  }

  public void setZone(Zone zone) {
    this.zone = zone;
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
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DishCategory that = (DishCategory) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return "DishCategory{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", photo='" + photo + '\'' +
        ", zone=" + zone +
        '}';
  }
}
