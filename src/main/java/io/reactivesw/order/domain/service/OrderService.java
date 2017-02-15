package io.reactivesw.order.domain.service;

import io.reactivesw.exception.ConflictException;
import io.reactivesw.exception.NotExistException;
import io.reactivesw.model.Money;
import io.reactivesw.order.application.model.CartView;
import io.reactivesw.order.application.model.InventoryRequest;
import io.reactivesw.order.application.model.OrderFromCartDraft;
import io.reactivesw.order.application.model.OrderView;
import io.reactivesw.order.application.model.PaymentView;
import io.reactivesw.order.application.model.mapper.OrderMapper;
import io.reactivesw.order.application.service.OrderRestClient;
import io.reactivesw.order.domain.model.Order;
import io.reactivesw.order.infrastructure.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
   * order rest client.
   */
  @Autowired
  private transient OrderRestClient orderRestClient;

  /**
   * order repository.
   */
  @Autowired
  private transient OrderRepository orderRepository;

  /**
   * Create order from cart order.
   *
   * @param draft the draft
   * @return the order
   */
  public OrderView createOrderFromCart(OrderFromCartDraft draft) {
    /* TODO: 17/2/7
    1. get cart
    2. update inventory
    3. checkout
    4. change cart status
     */
    LOG.debug("enter createOrderFromCart, draft is : {}", draft.toString());

    CartView cart = orderRestClient.getCart(draft.getId(), draft.getVersion());

    orderRestClient.changeInventoryEntry(getInventoryRequest(cart));

    Money amount = cart.getTotalPrice();

    PaymentView payment = orderRestClient.checkout(cart.getCustomerId(), amount.getCentAmount(),
        draft
        .getPaymentMethodToken());

    Order entity = OrderMapper.of(cart, payment.getId());
    Order savedEntity = orderRepository.save(entity);

    OrderView result = OrderMapper.entityToModel(savedEntity);

    LOG.debug("end createOrderFromCart, result is : {}", result);
    return result;
  }

  /**
   * get inventory request by cart.
   *
   * @param cart Cart
   * @return list of Inventory Request
   */
  private List<InventoryRequest> getInventoryRequest(CartView cart) {
    List<InventoryRequest> result = cart.getLineItems().parallelStream().map(
        lineItem -> {
          return new InventoryRequest(
              lineItem.getProductVariant().getSku(), lineItem.getQuantity());
        }
    ).collect(Collectors.toList());

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
    OrderView result = OrderMapper.entityToModel(entity);

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
