package io.reactivesw.order.application.model.mapper;

import io.reactivesw.model.Reference;
import io.reactivesw.order.application.model.CartView;
import io.reactivesw.order.application.model.OrderView;
import io.reactivesw.order.domain.model.Order;
import io.reactivesw.order.infrastructure.enums.OrderState;
import io.reactivesw.order.infrastructure.enums.ReferenceTypes;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Davis on 17/2/6.
 */
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
    entity.setAnonymousId(cart.getAnonymousId());
    entity.setLineItems(LineItemMapper.modelToEntity(cart.getLineItems()));
    entity.setTotalPrice(MoneyMapper.modelToEntity(cart.getTotalPrice()));
    entity.setTaxedPrice(TaxedPriceMapper.modelToEntity(cart.getTaxedPrice()));
    entity.setShippingAddress(ShippingAddressMapper.modelToEntity(cart.getShippingAddress()));
    entity.setBillingAddress(BillingAddressMapper.modelToEntity(cart.getBillingAddress()));
    entity.setTaxMode(cart.getTaxMode());
    entity.setCountry(cart.getCountry());
    entity.setOrderState(OrderState.Complete);
    entity.setShippingInfo(ShippingInfoMapper.modelToEntity(cart.getShippingInfo()));
    entity.setPaymentInfo(paymentId);

    return entity;
  }

  /**
   * Entity to model order.
   *
   * @param entity the entity
   * @return the order
   */
  public static OrderView entityToModel(Order entity) {
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
    model.setTaxMode(entity.getTaxMode());
    model.setCountry(entity.getCountry());
    model.setOrderState(entity.getOrderState());
    model.setState(null);
    model.setShipmentState(null);
    model.setShippingInfo(ShippingInfoMapper.entityToModel(entity.getShippingInfo()));
    model.setCart(null);
    model.setPaymentInfo(new Reference(ReferenceTypes.PAYMENT.toString(), entity.getPaymentInfo()));
    model.setInventoryMode(null);

    return model;
  }
}
