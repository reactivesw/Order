package io.reactivesw.order.application.service;

import io.reactivesw.exception.NotExistException;
import io.reactivesw.model.Money;
import io.reactivesw.order.application.model.CartView;
import io.reactivesw.order.application.model.InventoryRequest;
import io.reactivesw.order.application.model.OrderFromCartDraft;
import io.reactivesw.order.application.model.OrderView;
import io.reactivesw.order.application.model.PaymentView;
import io.reactivesw.order.domain.service.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Davis on 17/3/17.
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
  private OrderRestClient restClient;

  /**
   * The Order service.
   */
  @Autowired
  private OrderService orderService;

  /**
   * Create order from cart order.
   *
   * @param draft the draft
   * @return the order
   */
  public OrderView createOrderFromCart(OrderFromCartDraft draft) {
    /*
    1. get cart
    2. update inventory
    3. checkout
    4. change cart status
    5. save order
     */
    LOG.debug("enter createOrderFromCart, draft is : {}", draft.toString());

    CartView cart = getCart(draft.getId(), draft.getVersion());

    changeInventory(cart);

    PaymentView payment = pay(draft.getCreditCardId(), cart);

    changeCartStatus(draft.getId());

    OrderView result = orderService.saveOrder(payment.getId(), cart);

    LOG.debug("end createOrderFromCart, result is : {}", result);

    return result;
  }


  /**
   * use rest client to get cart.
   *
   * @param id      cart id
   * @param version cart version
   * @return cart view
   */
  private CartView getCart(String id, Integer version) {
    LOG.debug("enter getCart, cart is is : {}, cart versioin is : {}", id, version);

    CartView result = restClient.getCart(id, version);

    if (result == null) {
      LOG.debug("can not find cart by id : {} and version : {}", id, version);
      throw new NotExistException("Cart Not Exist");
    }

    LOG.debug("end getCart");

    return result;
  }

  /**
   * use rest client to change inventory.
   *
   * @param cart cart view
   */
  private void changeInventory(CartView cart) {
    LOG.debug("enter changeInventory, cart id is : {}", cart.getId());

    List<InventoryRequest> inventoryRequests = getInventoryRequest(cart);

    restClient.changeInventoryEntry(inventoryRequests);

    LOG.debug("end changeInventory");
  }

  /**
   * use rest client to pay for cart.
   * @param creditCardId credit card id
   * @param cart cart view
   * @return payment view
   */
  private PaymentView pay(String creditCardId, CartView cart) {
    LOG.debug("enter pay, credit card id is : {}, cart view is : ", creditCardId, cart);

    Money amount = cart.getTotalPrice();

    PaymentView paymentView =
        restClient.checkout(cart.getCustomerId(), amount.getCentAmount(), creditCardId);

    LOG.debug("end pay, payment view is : {}", paymentView);

    return paymentView;
  }

  /**
   * use rest client to change cart status.
   * @param cartId cart id
   */
  private void changeCartStatus(String cartId) {
    LOG.debug("enter changeCartStatus, cart id is : {}", cartId);

    // TODO: 17/3/17 use rest client to change cart status

    LOG.debug("end changeCartStatus");
  }

  /**
   * get inventory request by cart.
   *
   * @param cart Cart
   * @return list of Inventory Request
   */
  private List<InventoryRequest> getInventoryRequest(CartView cart) {
    List<InventoryRequest> result = cart.getLineItems().stream().map(
        lineItem -> {
          return new InventoryRequest(
              lineItem.getProductVariant().getSku(), lineItem.getQuantity());
        }
    ).collect(Collectors.toList());

    return result;
  }
}
