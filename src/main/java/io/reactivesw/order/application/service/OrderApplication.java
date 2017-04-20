package io.reactivesw.order.application.service;

import io.reactivesw.order.application.model.AddressView;
import io.reactivesw.order.application.model.CartView;
import io.reactivesw.order.application.model.OrderView;
import io.reactivesw.order.application.model.mapper.OrderMapper;
import io.reactivesw.order.domain.model.Order;
import io.reactivesw.order.domain.service.EventService;
import io.reactivesw.order.domain.service.OrderService;
import io.reactivesw.order.infrastructure.enums.OrderStatus;
import io.reactivesw.order.infrastructure.exception.BuildOrderException;
import io.reactivesw.order.infrastructure.exception.CheckoutCartException;
import io.reactivesw.order.infrastructure.exception.GetAddressException;
import io.reactivesw.order.infrastructure.update.UpdateAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
   * The event repository.
   */
  @Autowired
  private transient EventService eventService;

  /**
   * place an order.
   *
   * @return Order view
   */
  @Transactional
  public OrderView place(String cartId, String addressId, String creditCardId) {
    LOG.debug("Enter. CartId: {}.", cartId);

    // build an order with cart and address.
    Order order = buildOrder(cartId, addressId);

    eventService.createMessage(order, creditCardId);

    LOG.debug("Exit. Order: {}.", order);
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
      order.setOrderStatus(OrderStatus.Created);

      // create the order.
      return orderService.save(order);
    } catch (CheckoutCartException | GetAddressException ex) {
      throw new BuildOrderException("Build order failed.");
    }
  }

}
