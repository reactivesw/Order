package io.reactivesw.order.application.controller;

import io.reactivesw.order.application.model.OrderView;
import io.reactivesw.order.application.model.PlaceOrderRequest;
import io.reactivesw.order.application.model.mapper.OrderMapper;
import io.reactivesw.order.application.service.OrderApplication;
import io.reactivesw.order.domain.model.Order;
import io.reactivesw.order.domain.service.OrderService;
import io.reactivesw.order.infrastructure.Router;
import io.reactivesw.order.infrastructure.update.UpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

/**
 * order controller, provider create, get, update, delete apis.
 */
@RestController
@CrossOrigin
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
   * checkout from cart.
   *
   * @return OrderView
   */
  @PostMapping(Router.ORDER_ROOT)
  public OrderView placeOrder(@RequestBody @Valid PlaceOrderRequest request) {
    LOG.info("enter. orderId: {}, PlaceOrderRequest: {}", request);

    //todo check if the customer is the id in token.
    OrderView orderView = orderApplication.place(request.getCartId(), request.getAddressId(),
        request.getCreditCardId());

    LOG.info("exit. order: {}.", orderView);
    return orderView;
  }

  /**
   * Gets order by id.
   *
   * @param orderId the order id
   * @return the order by id
   */
  @GetMapping(Router.ORDER_WITH_ID)
  public OrderView getOrderById(@PathVariable(Router.ORDER_ID) String orderId) {
    LOG.info("enter. orderId: {}.", orderId);

    Order result = orderService.getById(orderId);

    LOG.info("exit. order: {}, orderId: {}.", result, orderId);
    return OrderMapper.toView(result);
  }

  /**
   * get orders by customerId.
   *
   * @param customerId
   * @return
   */
  @GetMapping(Router.ORDER_ROOT)
  public List<OrderView> getOrdersByCustomerId(@RequestParam String customerId) {
    LOG.info("enter. order: {}.", customerId);

    List<Order> orders = orderService.getByCustomerId(customerId);

    LOG.info("exit. order: {}, customerId: {}.", orders, customerId);
    return OrderMapper.toView(orders);
  }

  /**
   * update cart with cart id.
   *
   * @param orderId String
   * @return Cart
   */
  @PutMapping(Router.ORDER_WITH_ID)
  public OrderView updateOrder(@PathVariable(Router.ORDER_ID) String orderId,
                               @RequestBody UpdateRequest updateRequest) {
    LOG.info("enter. id: {}, updateRequest: {}.", orderId, updateRequest);

    OrderView order = orderApplication.updateOrder(orderId, updateRequest.getVersion(),
        updateRequest.getActions());

    LOG.info("exit. order: {}, orderId: {}, updateRequest: {}.", order, orderId, updateRequest);
    return order;
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
    LOG.info("enter. order: {}.", orderId);

    orderService.deleteOrder(orderId, version);

    LOG.info("exit. deleteOrder.");
  }
}
