package cabare.entity.model;

import java.time.LocalDate;
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

  @ManyToOne
  @JoinColumn(name = "dish_id")
  private Dish dish;

  @ManyToOne
  @JoinColumn(name = "ingredient_id")
  private Ingredient ingredient;

  @Column(name = "quantity")
  private Double quantity;

  @Column(name = "archived")
  private boolean archived;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
    return archived;
  }

  public void setArchived(boolean archived) {
    this.archived = archived;
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
    return Objects.equals(dish, that.dish) &&
           Objects.equals(quantity, that.quantity);
  }

  @Override
  public int hashCode() {

    return Objects.hash(dish, quantity);
  }

  @Override
  public String toString() {
    return "Calculation{" +
        "id=" + id +
        ", dish=" + dish +
        ", ingredient=" + ingredient +
        ", quantity=" + quantity +
        ", archived=" + archived +
        '}';
  }
}
