package io.reactivesw.order.application.model;

import io.reactivesw.model.Money;
import lombok.Data;

import java.util.List;

/**
 * cart view, used to receive data from cart service.
 */
@Data
public class CartView {

  /**
   * uuid.
   */
  private String id;

  /**
   * version.
   */
  private Integer version;

  /**
   * customerId.
   */
  private String customerId;

  /**
   * line items.
   */
  private List<LineItemView> lineItems;

  /**
   * total price build the cart.
   */
  private Money totalPrice;

  /**
   * country.
   */
  private String country;

  /**
   * currency code.
   */
  private String currencyCode;
}
