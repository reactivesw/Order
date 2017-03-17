package io.reactivesw.order.application.controller;

import io.reactivesw.order.application.model.OrderFromCartDraft;
import io.reactivesw.order.application.model.OrderView;
import io.reactivesw.order.application.service.OrderApplication;
import io.reactivesw.order.domain.service.OrderService;
import io.reactivesw.order.infrastructure.Router;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * Created by Davis on 17/2/6.
 */
@RestController
public class OrderController {
  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

  /**
   * order service.
   */
  @Autowired
  private transient OrderService orderService;

  /**
   * The Order application.
   */
  @Autowired
  private transient OrderApplication orderApplication;

  /**
   * create order from cart.
   *
   * @param draft OrderFromCartDraft
   * @return Order order
   */
  @PostMapping(Router.ORDER_ROOT)
  public OrderView createOrderFromCart(@RequestBody @Valid OrderFromCartDraft draft) {
    LOG.debug("enter createOrderFromCart, draft is : {}", draft.toString());

    OrderView result = orderApplication.createOrderFromCart(draft);

    LOG.debug("end createOrderFromCart, result is : {}", result.toString());

    return result;
  }


  /**
   * Gets order by id.
   *
   * @param orderId the order id
   * @return the order by id
   */
  @GetMapping(Router.ORDER_WITH_ID)
  public OrderView getOrderById(@PathVariable(Router.ORDER_ID) String orderId) {
    LOG.debug("enter getOrderById, order id is : {}", orderId);

    OrderView result = orderService.getOrderById(orderId);

    LOG.debug("end getOrderById, result is : {}", result);

    return result;
  }

  /**
   * Delete order.
   *
   * @param orderId the order id
   * @param version the order version
   */
  @DeleteMapping(Router.ORDER_WITH_ID)
  public void deleteOrder(@PathVariable(Router.ORDER_ID) String orderId,
                          @RequestParam Integer version) {
    LOG.debug("enter deleteOrder, order id is : {}", orderId);

    orderService.deleteOrder(orderId, version);

    LOG.debug("end deleteOrder");
  }
}
