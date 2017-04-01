package io.reactivesw.order.application.model;

import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * place order.
 */
@Data
public class PlaceOrderRequest implements Serializable {

  /**
   * auto generated serial version id.
   */
  private static final long serialVersionUID = 2617384244612126209L;
  /**
   * credit card id.
   */
  @NotNull
  private String creditCardId;

  /**
   * credit card id.
   */
  @NotNull
  private String addressId;

  /**
   * credit card id.
   */
  @NotNull
  private String customerId;

  /**
   * credit card id.
   */
  @NotNull
  private String cartId;
}
