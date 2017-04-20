package io.reactivesw.order.application.model.event;

import io.reactivesw.model.Money;
import lombok.Data;

import java.util.List;

/**
 * Order created event model.
 */
@Data
public class OrderCreatedEvent {

  /**
   * order id.
   */
  private String orderId;

  /**
   * payment id.
   */
  private String paymentId;

  /**
   * shipping address id.
   */
  private String shippingAddressId;

  /**
   * inventory request list.
   */
  private List<InventoryRequest> inventoryRequests;

  /**
   * total amount of this order.
   */
  private Money totalAmount;
}
