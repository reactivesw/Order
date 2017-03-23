package io.reactivesw.order.application.service;

import io.reactivesw.exception.NotExistException;
import io.reactivesw.order.application.model.CartView;
import io.reactivesw.order.domain.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
