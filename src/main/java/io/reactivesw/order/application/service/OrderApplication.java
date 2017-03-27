package io.reactivesw.order.application.service;

import io.reactivesw.exception.NotExistException;
import io.reactivesw.order.application.model.CartView;
import io.reactivesw.order.application.model.OrderView;
import io.reactivesw.order.application.model.mapper.OrderMapper;
import io.reactivesw.order.domain.model.Order;
import io.reactivesw.order.domain.service.OrderService;
import io.reactivesw.order.infrastructure.exception.CheckoutFailedException;
import io.reactivesw.order.infrastructure.update.UpdateAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
   * create order from cart Id.
   *
   * @param cartId
   * @return
   */
  public OrderView checkout(String cartId) {
    LOG.debug("enter: cartId: {}", cartId);

    try {
      CartView cart = restClient.getCart(cartId);
      Order order = OrderMapper.of(cart);
      Order result = orderService.createWithSample(order);

      LOG.debug("enter: order: {}", result);
      return OrderMapper.toView(result);
    } catch (Exception ex) {
      throw new CheckoutFailedException("Checkout failed! " + ex.getMessage());
    }
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
}
