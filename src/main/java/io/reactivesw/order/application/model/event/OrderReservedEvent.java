package io.reactivesw.order.application.model.event;

import lombok.Data;

/**
 * Order reserved event.
 */
@Data
public class OrderReservedEvent {

  /**
   * Order id.
   */
  private String orderId;

  /**
   * Reserved result.
   */
  private boolean result;

  /**
   * Message.
   */
  private String message;
}
