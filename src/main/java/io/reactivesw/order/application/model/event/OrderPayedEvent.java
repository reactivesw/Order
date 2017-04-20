package io.reactivesw.order.application.model.event;

import lombok.Data;

/**
 * Order payed event.
 */
@Data
public class OrderPayedEvent {

  /**
   * Order id.
   */
  private String orderId;

  /**
   * Payment Id.
   */
  private String paymentId;

  /**
   * Payment result.
   */
  private boolean result;

  /**
   * Pay message.
   */
  private String message;
}
