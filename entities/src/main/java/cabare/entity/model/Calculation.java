package cabare.entity.model;

import java.util.Date;
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
@Table(name = "calculation")
public class Calculation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "number")
  private Long number;

  @Column(name = "data")
  private Date date;

  @ManyToOne
  @JoinColumn(name = "cabare_id", nullable = false)
  private Cabare cabare;

  @ManyToOne
  @JoinColumn(name = "dish_id")
  private Dish dish;

  @ManyToOne
  @JoinColumn(name = "ingredient_id")
  private Ingredient ingredient;

  @Column(name = "quantity")
  private Double quantity;

  @Column(name = "is_archived")
  private boolean isArchived;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getNumber() {
    return number;
  }

  public void setNumber(Long number) {
    this.number = number;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Cabare getCabare() {
    return cabare;
  }

  public void setCabare(Cabare cabare) {
    this.cabare = cabare;
  }

  public Dish getDish() {
    return dish;
  }

  public void setDish(Dish dish) {
    this.dish = dish;
  }

  public Ingredient getIngredient() {
    return ingredient;
  }

  public void setIngredient(Ingredient ingredient) {
    this.ingredient = ingredient;
  }

  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public boolean isArchived() {
    return isArchived;
  }

  public void setArchived(boolean archived) {
    isArchived = archived;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Calculation)) {
      return false;
    }
    Calculation that = (Calculation) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(number, that.number) &&
        Objects.equals(cabare, that.cabare);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, number, cabare);
  }

  @Override
  public String toString() {
    return "Calculation{" +
        "id=" + id +
        ", number=" + number +
        ", date=" + date +
        ", cabare=" + cabare +
        ", dish=" + dish +
        ", ingredient=" + ingredient +
        ", quantity=" + quantity +
        ", isArchived=" + isArchived +
        '}';
  }
}
