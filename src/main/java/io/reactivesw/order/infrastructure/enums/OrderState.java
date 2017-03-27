package io.reactivesw.order.infrastructure.enums;

/**
 * Created by Davis on 16/11/17.
 */

public enum OrderState {

  /**
   * Created order state.
   */
  Created,
  /**
   * Confirmed order state.
   */
  Confirmed,
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
