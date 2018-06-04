package cabare.entity.model;

import cabare.entity.domain.Money;
import cabare.entity.domain.MoneyConverter;
import cabare.entity.domain.PayStatus;
import cabare.entity.domain.PayType;
import cabare.entity.domain.SaleType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class BillWaiter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "served_table")
  private Long servedTable;

  @Column(name = "open_bill_time", columnDefinition = "datetime")
  private LocalDateTime openBillTime;

  @Column(name = "close_bill_time", columnDefinition = "datetime")
  private LocalDateTime closeBillTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id")
  private Employee employee;

  @Column(name = "table_number")
  private Integer tableNumber;

  @Column(name = "number_of_persons")
  private Integer numberOfPersons;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "discount_id", nullable = true)
  private Discount discount;

  @Column(name = "sale_type_id")
  @Enumerated(EnumType.ORDINAL)
  private SaleType saleType;

  @Column(name = "pay_type_id")
  @Enumerated(EnumType.ORDINAL)
  private PayType payType;

  @Column(name = "pay_status_id")
  @Enumerated(EnumType.ORDINAL)
  private PayStatus payStatus;

  @Convert(converter = MoneyConverter.class)
  @Column(name = "money_paid")
  private Money moneyPaid = Money.ZERO;

  @Convert(converter = MoneyConverter.class)
  @Column(name = "discounted_sum")
  private Money moneyDiscounted = Money.ZERO;

  @Column(name = "is_opened", nullable = false, columnDefinition = "BIT(1) DEFAULT 1")
  private boolean opened = true;

  @Column(name = "is_active_shift", columnDefinition = "BIT(1) DEFAULT 1")
  private boolean activeShift = true;

  @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<OrderItem> orderItems = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "cabare_id")
  private Cabare cabare;

  public void addOrderItem(OrderItem orderItem) {
    this.orderItems.add(orderItem);
    orderItem.setBillWaiter(this);
  }

  public void addOrderItems(Collection<OrderItem> orderItems) {
    orderItems.stream().forEach(oi -> this.addOrderItem(oi));
  }

  public void removeOrderItem(OrderItem orderItem) {
    this.orderItems.remove(orderItem);
    orderItem.setBillWaiter(null);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getServedTable() {
    return servedTable;
  }

  public void setServedTable(Long servedTable) {
    this.servedTable = servedTable;
  }

  public LocalDateTime getOpenBillTime() {
    return openBillTime;
  }

  public void setOpenBillTime(LocalDateTime openBillTime) {
    this.openBillTime = openBillTime;
  }

  public LocalDateTime getCloseBillTime() {
    return closeBillTime;
  }

  public void setCloseBillTime(LocalDateTime closeBillTime) {
    this.closeBillTime = closeBillTime;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Integer getTableNumber() {
    return tableNumber;
  }

  public void setTableNumber(Integer tableNumber) {
    this.tableNumber = tableNumber;
  }

  public Integer getNumberOfPersons() {
    return numberOfPersons;
  }

  public void setNumberOfPersons(Integer numberOfPersons) {
    this.numberOfPersons = numberOfPersons;
  }

  public Discount getDiscount() {
    return discount;
  }

  public void setDiscount(Discount discount) {
    this.discount = discount;
  }

  public SaleType getSaleType() {
    return saleType;
  }

  public void setSaleType(SaleType saleType) {
    this.saleType = saleType;
  }

  public PayType getPayType() {
    return payType;
  }

  public void setPayType(PayType payType) {
    this.payType = payType;
  }

  public PayStatus getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(PayStatus payStatus) {
    this.payStatus = payStatus;
  }

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }

  public Money getMoneyPaid() {
    return moneyPaid;
  }

  public void setMoneyPaid(Money moneyPaid) {
    this.moneyPaid = moneyPaid;
  }

  public Money getMoneyDiscounted() {
    return moneyDiscounted;
  }

  public void setMoneyDiscounted(Money moneyDiscounted) {
    this.moneyDiscounted = moneyDiscounted;
  }

  public boolean isOpened() {
    return opened;
  }

  public void setOpened(boolean opened) {
    this.opened = opened;
  }

  public boolean isActiveShift() {
    return activeShift;
  }

  public void setActiveShift(boolean activeShift) {
    this.activeShift = activeShift;
  }

  public Cabare getCabare() {
    return cabare;
  }

  public void setCabare(Cabare cabare) {
    this.cabare = cabare;
  }

  public Money getTotalPrice() {
    Money cost = Money.ZERO;
    for (OrderItem orderItem : this.getOrderItems()) {
      cost = cost.add(orderItem.getTotalPrice());
    }
    return cost;
  }

  public void addPayment(Money payment) {
    this.moneyPaid = this.moneyPaid.add(payment);
  }
}
