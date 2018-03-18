package cabare.entity.model;

import cabare.entity.domain.Money;
import cabare.entity.domain.MoneyConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "discount")
public class Discount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "card_number")
  private String cardNumber;

  @Column(name = "total_paid")
  @Convert(converter = MoneyConverter.class)
  private Money totalPaid;

  @Column(name = "discount_size")
  private Integer size;

  @Column(name = "activated")
  private Boolean activated = true;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public Money getTotalPaid() {
    return totalPaid;
  }

  public void setTotalPaid(Money totalPaid) {
    this.totalPaid = totalPaid;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public Boolean isActivated() {
    return activated;
  }

  public void setActivated(Boolean activated) {
    this.activated = activated;
  }
}
