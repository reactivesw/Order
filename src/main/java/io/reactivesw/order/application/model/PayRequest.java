package io.reactivesw.order.application.model;

import io.reactivesw.model.Money;

/**
 * pay request for place order.
 */
public class PayRequest {

  /**
   * customer id of the order.
   */
  private String customerId;

  /**
   * amount of order.
   */
  private Money amount;

  /**
   * credit cart id.
   */
  private String creditCardId;

  /**
   * default constructor.
   */
  public PayRequest() {
    // do nothing.
  }

  /**
   * constructor with full data.
   *
   * @param customerId   String
   * @param amount       Money
   * @param creditCardId String
   */
  public PayRequest(String customerId, Money amount, String creditCardId) {
    this.customerId = customerId;
    this.amount = amount;
    this.creditCardId = creditCardId;
  }

  /**
   * get customer id.
   *
   * @return string
   */
  public String getCustomerId() {
    return customerId;
  }

  /**
   * set customer id.
   *
   * @param customerId String
   */
  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  /**
   * get amount.
   *
   * @return Money
   */
  public Money getAmount() {
    return amount;
  }

  /**
   * set amount.
   *
   * @param amount Money
   */
  public void setAmount(Money amount) {
    this.amount = amount;
  }

  /**
   * get credit card id.
   *
   * @return String
   */
  public String getCreditCardId() {
    return creditCardId;
  }

  /**
   * set credit card id.
   *
   * @param creditCardId String
   */
  public void setCreditCardId(String creditCardId) {
    this.creditCardId = creditCardId;
  }
}
