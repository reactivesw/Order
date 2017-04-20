package io.reactivesw.order.application.model.mapper;

import io.reactivesw.order.application.model.InventoryRequest;
import io.reactivesw.order.application.model.event.OrderCreatedEvent;
import io.reactivesw.order.domain.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Order created event mapper.
 */
public final class OrderCreatedEventMapper {

  /**
   * Default private constructor.
   */
  private OrderCreatedEventMapper() {
    // Do nothing.
  }

  /**
   * Build an event from order and credit card id.
   * @param order order
   * @param creditCardId String
   * @return OrderCreateEvent
   */
  public static OrderCreatedEvent build(Order order, String creditCardId) {
    OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent();
    orderCreatedEvent.setOrderId(order.getId());
    orderCreatedEvent.setPaymentMethodId(creditCardId);
    orderCreatedEvent.setShippingAddressId(order.getShippingAddress().getId());
    orderCreatedEvent.setTotalAmount(MoneyMapper.toView(order.getTotalPrice()));

    List<InventoryRequest> inventoryRequestList = new ArrayList<>();
    order.getLineItems().stream().forEach(
        lineItem -> {
          InventoryRequest request = new InventoryRequest();
          request.setSkuName(lineItem.getSku());
          request.setQuantity(lineItem.getQuantity());
          inventoryRequestList.add(request);
        }
    );
    orderCreatedEvent.setInventoryRequests(inventoryRequestList);
    return orderCreatedEvent;
  }
}
