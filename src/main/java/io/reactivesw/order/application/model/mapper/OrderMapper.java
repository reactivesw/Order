package io.reactivesw.order.application.model.mapper;

import io.reactivesw.order.application.model.CartView;
import io.reactivesw.order.application.model.OrderView;
import io.reactivesw.order.domain.model.Order;
import io.reactivesw.order.infrastructure.enums.OrderState;

public final class OrderMapper {
  /**
   * Instantiates a new Order mapper.
   */
  private OrderMapper() {
  }


  /**
   * Of order entity.
   *
   * @param cart    the cart
   * @param paymentId the payment id
   * @return the order entity
   */
  public static Order of(CartView cart, String paymentId) {
    Order entity = new Order();

    entity.setCompletedAt(null);
    entity.setOrderName(null);
    entity.setCustomerId(cart.getCustomerId());
    entity.setLineItems(LineItemMapper.modelToEntity(cart.getLineItems()));
    entity.setTotalPrice(MoneyMapper.modelToEntity(cart.getTotalPrice()));
    entity.setCountry(cart.getCountry());
    entity.setOrderState(OrderState.Complete);

    return entity;
  }

  /**
   * Entity to model order.
   *
   * @param entity the entity
   * @return the order
   */
  public static OrderView mapToModel(Order entity) {
    OrderView model = new OrderView();

    model.setId(entity.getId());
    model.setVersion(entity.getVersion());
    model.setCreatedAt(entity.getCreatedAt());
    model.setLastModifiedAt(entity.getLastModifiedAt());
    model.setCompletedAt(entity.getCompletedAt());
    model.setOrderNumber(entity.getOrderName());
    model.setCustomerId(entity.getCustomerId());
    model.setAnonymousId(entity.getAnonymousId());
    model.setLineItems(LineItemMapper.entityToModel(entity.getLineItems()));
    model.setTotalPrice(MoneyMapper.entityToModel(entity.getTotalPrice()));
    model.setCountry(entity.getCountry());
    model.setOrderState(entity.getOrderState());

    return model;
  }
}
