package io.reactivesw.order.domain.service;

import io.reactivesw.exception.ConflictException;
import io.reactivesw.exception.NotExistException;
import io.reactivesw.order.domain.model.Order;
import io.reactivesw.order.domain.model.value.LineItem;
import io.reactivesw.order.domain.model.value.MoneyValue;
import io.reactivesw.order.infrastructure.repository.OrderRepository;
import io.reactivesw.order.infrastructure.update.UpdateAction;
import io.reactivesw.order.infrastructure.update.UpdaterService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * order service.
 */
@Service
public class OrderService {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

  /**
   * order repository.
   */
  @Autowired
  private transient OrderRepository orderRepository;

  /**
   * cart update service.
   */
  @Autowired
  private transient UpdaterService orderUpdater;

  /**
   * order LineItem service.
   */
  @Autowired
  private transient LineItemService lineItemService;

  /**
   * Save order.
   *
   * @param order sample
   * @return the order view
   */
  public Order save(Order order) {
    LOG.debug("enter: order: {},", order);

    Order savedOrder = orderRepository.save(order);

    LOG.debug("end: savedOrder : {}", savedOrder);

    return savedOrder;
  }

  /**
   * Gets order by id.
   *
   * @param orderId the order id
   * @return the order by id
   */
  public Order getById(String orderId) {
    LOG.debug("enter getById, order id is : {}", orderId);

    Order result = orderRepository.findOne(orderId);

    if (result == null) {
      LOG.debug("can not find order by id : {}", orderId);
      throw new NotExistException("Order Not Exist");
    }

    LOG.debug("end getById, result is : {}", result);

    return result;
  }

  /**
   * Gets order by customerId.
   *
   * @param customerId the customerID
   * @return the order by id
   */
  public List<Order> getByCustomerId(String customerId) {
    LOG.debug("enter getById, CustomerId : {}", customerId);

    List<Order> entities = orderRepository.findByCustomerId(customerId);

    LOG.debug("end getById, result is : {}", entities);

    return entities;
  }

  /**
   * update order with actions.
   *
   * @param id
   * @param version
   * @param actions
   * @return
   */
  public Order updateOrder(String id, Integer version, List<UpdateAction> actions) {
    Order order = this.getById(id);

    this.validateVersion(version, order.getVersion());
    //update data from action
    actions.stream().forEach(
        action -> orderUpdater.handle(order, action)
    );

    //recalculate the order.
    calculateCartPrice(order);
    return this.orderRepository.save(order);
  }

  /**
   * Delete order.
   *
   * @param orderId the order id
   * @param version the order version
   */
  public void deleteOrder(String orderId, Integer version) {
    LOG.debug("enter deleteOrder, order id is : {}", orderId);

    Order entity = getById(orderId);
    validateVersion(version, entity.getVersion());
    orderRepository.delete(entity);

    LOG.debug("end deleteOrder");
  }

  /**
   * validate order version.
   */
  private void validateVersion(Integer inputVersion, Integer curVersion) {
    if (!curVersion.equals(inputVersion)) {
      LOG.debug("order version can not match, expect version is : {}, real version is : {}",
          inputVersion, curVersion);
      throw new ConflictException("Order Version Not Match");
    }
  }

  /**
   * calculate order total price.
   *
   * @param order Order
   */
  public void calculateCartPrice(Order order) {
    LOG.debug("enter. order before calculate: {}", order);
    List<LineItem> items = order.getLineItems();
    int lineItemTotalPrice = 0;
    MoneyValue orderTotal = new MoneyValue();
    if (items != null) {

      items.stream().forEach(
          lineItem -> {
            lineItemService.calculateItemPrice(lineItem);
            orderTotal.setCurrencyCode(lineItem.getPrice().getCurrencyCode());
          }
      );
      //count total price of all line item
      lineItemTotalPrice = items.stream().mapToInt(
          lineItem -> lineItem.getTotalPrice().getCentAmount()
      ).sum();
    }

    int cartTotalPrice = lineItemTotalPrice;

    // TODO get the currency from the merchant.
    orderTotal.setCentAmount(cartTotalPrice);
    order.setTotalPrice(orderTotal);
    LOG.debug("exit: order after calculate: {}", order);
  }

  /**
   * Generate an order number when places order. Reference: http://stackoverflow.com/questions/325443/likelihood-of-collision-using-most-significant-bits-of-a-uuid-in-java
   *
   * @return orderNumber
   */
  public String generateOrderNumber() {
    // getLeastSignificantBits may get negative value, so and with Long.MAX_VALUE to ensure it is positive
    Long orderNumber = UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE;
    return orderNumber.toString();
  }
}
