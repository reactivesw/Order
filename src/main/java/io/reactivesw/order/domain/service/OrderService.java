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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;


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
  //Create a new transaction if an exception occurs rather than it's rollback.
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Order save(Order order) throws DataIntegrityViolationException {
    LOG.debug("enter: order: {},", order);
    Order savedOrder = null;
    try {
      savedOrder = orderRepository.saveAndFlush(order);
    } catch (DataIntegrityViolationException ex) {
      LOG.debug("OrderNumber is not unique, save failed");
      throw new DataIntegrityViolationException("OrderNumber must be unique");

    }

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
   * Generate an order number when places order.
   *
   * @return orderNumber
   */
  public long generateOrderNumber() {
    SecureRandom ng = new SecureRandom();
    long orderNumber = 0;
    byte[] randomBytes = new byte[8];
    ng.nextBytes(randomBytes);
    for (int i = 0; i < randomBytes.length; i++) {
      orderNumber = orderNumber << 8 | randomBytes[i] & 0xff;
    }
    return Math.abs(orderNumber >> 31); //Make ensure it is a positive number.
  }
}
