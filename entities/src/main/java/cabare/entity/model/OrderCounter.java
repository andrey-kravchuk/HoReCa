package cabare.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_counter")
public class OrderCounter {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "counter")
  private long counter;

  @ManyToOne
  @JoinColumn(name = "cabare_id")
  private Cabare cabare;

  public OrderCounter() {
  }

  public long next() {
    return ++counter;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public long getCounter() {
    return counter;
  }

  public void setCounter(long counter) {
    this.counter = counter;
  }

  public Cabare getCabare() {
    return cabare;
  }

  public void setCabare(Cabare cabare) {
    this.cabare = cabare;
  }
}