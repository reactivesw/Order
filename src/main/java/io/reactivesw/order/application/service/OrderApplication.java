package io.reactivesw.order.application.service;

import io.reactivesw.order.application.model.AddressView;
import io.reactivesw.order.application.model.CartView;
import io.reactivesw.order.application.model.InventoryRequest;
import io.reactivesw.order.application.model.OrderView;
import io.reactivesw.order.application.model.PayRequest;
import io.reactivesw.order.application.model.PaymentView;
import io.reactivesw.order.application.model.mapper.MoneyMapper;
import io.reactivesw.order.application.model.mapper.OrderMapper;
import io.reactivesw.order.domain.model.Order;
import io.reactivesw.order.domain.model.value.LineItem;
import io.reactivesw.order.domain.service.OrderService;
import io.reactivesw.order.infrastructure.enums.OrderState;
import io.reactivesw.order.infrastructure.exception.BuildOrderException;
import io.reactivesw.order.infrastructure.exception.CheckoutCartException;
import io.reactivesw.order.infrastructure.exception.GetAddressException;
import io.reactivesw.order.infrastructure.update.UpdateAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * order application.
 */
@Service
public class OrderApplication {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(OrderApplication.class);

  /**
   * The Rest client.
   */
  @Autowired
  private transient OrderRestClient restClient;

  /**
   * The Order service.
   */
  @Autowired
  private transient OrderService orderService;

  /**
   * place an order.
   *
   * @return Order view
   */
  public OrderView place(String cartId, String addressId, String creditCardId) {
    LOG.debug("enter: cartId: {}", cartId);

    // build an order with cart and address.
    Order order = buildOrder(cartId, addressId);
    // change inventory.
    changeInventory(order);
    // call payment service to pay the order.
    orderPay(order, creditCardId);

    orderService.save(order);

    LOG.debug("enter: order: {}", order);
    return OrderMapper.toView(order);
  }

  /**
   * use rest client to get cart.
   *
   * @param id      cart id
   * @param version cart version
   * @return cart view
   */
  public OrderView updateOrder(String id, Integer version, List<UpdateAction> actions) {
    LOG.debug("enter: id{}, version: {}, actions: {}", id, version, actions);

    Order result = orderService.updateOrder(id, version, actions);

    LOG.debug("exit: result: {}", result);
    return OrderMapper.toView(result);
  }

  /**
   * build an order with cart and address.
   *
   * @param cartId    String
   * @param addressId String
   * @return Order
   */
  private Order buildOrder(String cartId, String addressId) {

    try {
      CartView cart = restClient.getCart(cartId);
      AddressView address = restClient.getAddress(addressId);
      Order order = OrderMapper.build(cart, address);
      orderService.calculateCartPrice(order);
      order.setOrderState(OrderState.Created);

      // create the order.
      return orderService.save(order);
    } catch (CheckoutCartException | GetAddressException ex) {
      throw new BuildOrderException("Build order failed.");
    }
  }

  /**
   * change product inventory.
   *
   * @param order Order
   */
  private void changeInventory(Order order) {
    List<LineItem> items = order.getLineItems();
    List<InventoryRequest> inventoryRequests = new ArrayList<>();
    items.stream().forEach(
        lineItem -> {
          InventoryRequest request = new InventoryRequest();
          request.setQuantity(lineItem.getQuantity());
          request.setSkuName(lineItem.getSku());
          inventoryRequests.add(request);
        }
    );
    restClient.changeProductInventory(inventoryRequests);
    order.setOrderState(OrderState.Reserved);
  }

  /**
   * pay.
   *
   * @param order        order
   * @param creditCartId credit card id
   * @return payment view
   */
  private void orderPay(Order order, String creditCartId) {
    PayRequest request = new PayRequest();
    request.setCustomerId(order.getCustomerId());
    request.setCreditCardId(creditCartId);
    request.setAmount(MoneyMapper.toView(order.getTotalPrice()));
    PaymentView paymentView = restClient.pay(request);

    order.setPaymentId(paymentView.getId());
    order.setOrderState(OrderState.Payed);
  }
}
