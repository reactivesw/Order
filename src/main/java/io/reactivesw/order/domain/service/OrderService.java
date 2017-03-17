package io.reactivesw.order.domain.service;

import io.reactivesw.exception.ConflictException;
import io.reactivesw.exception.NotExistException;
import io.reactivesw.order.application.model.CartView;
import io.reactivesw.order.application.model.OrderView;
import io.reactivesw.order.application.model.mapper.OrderMapper;
import io.reactivesw.order.domain.model.Order;
import io.reactivesw.order.infrastructure.repository.OrderRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Davis on 17/2/6.
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
   * Save order order.
   *
   * @param paymentId the payment id
   * @param cart      the cart
   * @return the order view
   */
  public OrderView saveOrder(String paymentId, CartView cart) {
    LOG.debug("enter saveOrder, payment id is : {}, cart view is : {}", paymentId, cart);

    Order entity = OrderMapper.of(cart, paymentId);

    Order savedEntity = orderRepository.save(entity);

    OrderView result = OrderMapper.mapToModel(savedEntity);

    LOG.debug("end createOrderFromCart, result is : {}", result);

    return result;
  }

  /**
   * Gets order by id.
   *
   * @param orderId the order id
   * @return the order by id
   */
  public OrderView getOrderById(String orderId) {
    LOG.debug("enter getOrderById, order id is : {}", orderId);

    Order entity = getOrderEntity(orderId);
    OrderView result = OrderMapper.mapToModel(entity);

    LOG.debug("end getOrderById, result is : {}", result);

    return result;
  }

  /**
   * get order entity.
   *
   * @param orderId the order id
   * @return the order entity
   */
  public Order getOrderEntity(String orderId) {
    LOG.debug("enter getOrderEntity, order is is : {}", orderId);

    Order result = orderRepository.findOne(orderId);

    if (result == null) {
      LOG.debug("can not find order by id : {}", orderId);
      throw new NotExistException("Order Not Exist");
    }

    LOG.debug("end getOrderEntity, result is : {}", result);

    return result;
  }

  /**
   * Delete order.
   *
   * @param orderId the order id
   * @param version the order version
   */
  public void deleteOrder(String orderId, Integer version) {
    LOG.debug("enter deleteOrder, order id is : {}", orderId);

    Order entity = getOrderEntity(orderId);
    validateVersion(entity, version);
    orderRepository.delete(entity);

    LOG.debug("end deleteOrder");
  }

  /**
   * validate order version.
   *
   * @param entity  order entity
   * @param version expect version
   */
  private void validateVersion(Order entity, Integer version) {
    if (!version.equals(entity.getVersion())) {
      LOG.debug("order version can not match, expect version is : {}, real version is : {}",
          version, entity.getVersion());
      throw new ConflictException("Order Version Not Match");
    }
  }
}
