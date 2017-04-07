package io.reactivesw.order.application.model.mapper;

import io.reactivesw.order.application.model.AddressView;
import io.reactivesw.order.application.model.CartView;
import io.reactivesw.order.application.model.OrderView;
import io.reactivesw.order.domain.model.Order;
import io.reactivesw.order.infrastructure.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * order mapper
 */
public final class OrderMapper {
  /**
   * private default constructor.
   */
  private OrderMapper() {
  }


  /**
   * Of order entity.
   *
   * @param cart the cart
   * @return the order entity
   */
  public static Order build(CartView cart, AddressView address) {
    Order entity = new Order();

    entity.setCustomerId(cart.getCustomerId());
    entity.setLineItems(LineItemMapper.toEntity(cart.getLineItems()));
    entity.setTotalPrice(MoneyMapper.toEntity(cart.getTotalPrice()));
    entity.setOrderStatus(OrderStatus.Created);

    entity.setShippingAddress(ShippingAddressMapper.toEntity(address));
    return entity;
  }

  /**
   * Entity to model order.
   *
   * @param entity the entity
   * @return the order
   */
  public static OrderView toView(Order entity) {
    OrderView model = new OrderView();

    model.setId(entity.getId());
    model.setVersion(entity.getVersion());
    model.setCreatedAt(entity.getCreatedAt());
    model.setLastModifiedAt(entity.getLastModifiedAt());
    model.setCompletedAt(entity.getCompletedAt());
    model.setCustomerId(entity.getCustomerId());
    model.setLineItems(LineItemMapper.toViews(entity.getLineItems()));
    model.setTotalPrice(MoneyMapper.toView(entity.getTotalPrice()));
    model.setOrderStatus(entity.getOrderStatus());
    model.setShippingAddress(ShippingAddressMapper.toView(entity.getShippingAddress()));

    return model;
  }

  /**
   * convert list build order to view.
   *
   * @param orders
   * @return
   */
  public static List<OrderView> toView(List<Order> orders) {
    List<OrderView> orderViews = new ArrayList<>();
    orders.stream().forEach(order -> orderViews.add(toView(order)));
    return orderViews;
  }
}
