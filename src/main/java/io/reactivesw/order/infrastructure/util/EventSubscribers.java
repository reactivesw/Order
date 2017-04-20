package io.reactivesw.order.infrastructure.util;

/**
 * Event subscriber ids used by this service.
 */
public final class EventSubscribers {

  /**
   * Order got payed event.
   */
  public static final String ORDER_PAYMENT_PAYED = "order-payment-payed";

  /**
   * Order got reserved event.
   */
  public static final String ORDER_INVENTORY_RESERVED = "order-inventory-reserved";
}
