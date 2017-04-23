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
   * Reserved inventory.
   */
  Reserved,
  /**
   * Reserve inventory failed.
   */
  ReservedFailed,
  /**
   * Payed.
   */
  Payed,
  /**
   * Payed failed.
   */
  PayFailed,
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
