package io.reactivesw.order.application.model.event;

import io.reactivesw.model.Money;
import io.reactivesw.order.application.model.InventoryRequest;

import lombok.Data;

import java.util.List;

/**
 * Order created event model.
 */
@Data
public class OrderCreatedEvent {

  /**
   * Order id.
   */
  private String orderId;

  /**
   * Payment id.
   */
  private String paymentMethodId;

  /**
   * Shipping address id.
   */
  private String shippingAddressId;

  /**
   * Inventory request list.
   */
  private List<InventoryRequest> inventoryRequests;

  /**
   * Total amount of this order.
   */
  private Money totalAmount;
}
