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
