package io.reactivesw.order.application.controller;

import io.reactivesw.order.application.model.OrderFromCartDraft;
import io.reactivesw.order.application.model.OrderView;
import io.reactivesw.order.domain.service.OrderService;
import io.reactivesw.order.infrastructure.Router;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
   * create order from cart.
   *
   * @param draft OrderFromCartDraft
   * @return Order order
   */
  @ApiOperation("Create Order from Cart")
  @PostMapping(Router.ORDER_ROOT)
  public OrderView createOrderFromCart(@RequestBody
                                       @ApiParam(value = "order from cart draft", required = true)
                                       @Valid OrderFromCartDraft draft) {
    LOG.debug("enter createOrderFromCart, draft is : {}", draft.toString());

    OrderView result = orderService.createOrderFromCart(draft);

    LOG.debug("end createOrderFromCart, result is : {}", result.toString());

    return result;
  }


  /**
   * Gets order by id.
   *
   * @param orderId the order id
   * @return the order by id
   */
  @ApiOperation("get order by id")
  @GetMapping(Router.ORDER_WITH_ID)
  public OrderView getOrderById(@PathVariable(Router.ORDER_ID)
                                @ApiParam(value = "order id", required = true)
                                    String orderId) {
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
  @ApiOperation("delete order")
  @DeleteMapping(Router.ORDER_WITH_ID)
  public void deleteOrder(@PathVariable(Router.ORDER_ID)
                          @ApiParam(value = "order id", required = true)
                              String orderId,
                          @RequestParam
                          @ApiParam(value = "order version", required = true)
                              Integer version) {
    LOG.debug("enter deleteOrder, order id is : {}", orderId);

    orderService.deleteOrder(orderId, version);

    LOG.debug("end deleteOrder");
  }
}
