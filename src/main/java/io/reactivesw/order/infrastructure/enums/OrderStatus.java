package io.reactivesw.order.infrastructure.enums;

/**
 * order status.
 */
public enum OrderStatus {

  /**
   * Created order state.
   */
  Created,
  /**
   * Confirmed order state.
   */
  Reserved,
  /**
   * payed.
   */
  Payed,
  /**
   * Shipped
   */
  Shipped,
  /**
   * Complete order state.
   */
  Complete,
  /**
   * Cancelled order state.
   */
  Cancelled;
}
