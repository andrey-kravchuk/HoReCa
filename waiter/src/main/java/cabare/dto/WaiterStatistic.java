package cabare.dto;

import cabare.entity.domain.Money;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WaiterStatistic {

  @JsonProperty(value = "opened_bill_count")
  private Integer openedBillCount;
  @JsonProperty(value = "closed_bill_count")
  private Integer closedBillCount;

  @JsonProperty(value = "opened_bill_sum")
  private String openedBillSum;
  @JsonProperty(value = "closed_cash_sum")
  private String closedCashSum;
  @JsonProperty(value = "closed_cashless_sum")
  private String closedCashlessSum;
  @JsonProperty(value = "closed_total_sum")
  private String closedTotalSum;

  @JsonProperty(value = "average_check_sum")
  private String averageCheckSum;
  @JsonProperty(value = "min_check")
  private String minCheck;
  @JsonProperty(value = "max_check")
  private String maxCheck;

  @JsonProperty(value = "served_tables")
  private Integer servedTables;
  @JsonProperty(value = "visitors_served")
  private Integer visitorsServed;


  public void setOpenedBillCount(Integer openedBillCount) {
    this.openedBillCount = openedBillCount;
  }

  public Integer getOpenedBillCount() {
    return openedBillCount;
  }

  public void setClosedBillCount(Integer closedBillCount) {
    this.closedBillCount = closedBillCount;
  }

  public Integer getClosedBillCount() {
    return closedBillCount;
  }

  public void setOpenedBillSum(Money openedBillSum) {
    this.openedBillSum = openedBillSum.toString();
  }

  public String getOpenedBillSum() {
    return openedBillSum;
  }

  public void setClosedCashSum(Money closedCashSum) {
    this.closedCashSum = closedCashSum.toString();
  }

  public String getClosedCashSum() {
    return closedCashSum;
  }

  public void setClosedCashlessSum(Money closedCashlessSum) {
    this.closedCashlessSum = closedCashlessSum.toString();
  }

  public String getClosedCashlessSum() {
    return closedCashlessSum;
  }

  public void setClosedTotalSum(Money closedTotalSum) {
    this.closedTotalSum = closedTotalSum.toString();
  }

  public String getClosedTotalSum() {
    return closedTotalSum;
  }

  public void setAverageCheckSum(Money averageCheckSum) {
    this.averageCheckSum = averageCheckSum.toString();
  }

  public String getAverageCheckSum() {
    return averageCheckSum;
  }

  public String getMinCheck() {
    return minCheck;
  }

  public void setMinCheck(Money minCheck) {
    this.minCheck = minCheck.toString();
  }

  public String getMaxCheck() {
    return maxCheck;
  }

  public void setMaxCheck(Money maxCheck) {
    this.maxCheck = maxCheck.toString();
  }

  public Integer getServedTables() {
    return servedTables;
  }

  public void setServedTables(Integer servedTables) {
    this.servedTables = servedTables;
  }

  public Integer getVisitorsServed() {
    return visitorsServed;
  }

  public void setVisitorsServed(Integer visitorsServed) {
    this.visitorsServed = visitorsServed;
  }
}
