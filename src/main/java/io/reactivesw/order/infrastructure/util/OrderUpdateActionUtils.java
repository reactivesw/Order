package io.reactivesw.order.infrastructure.util;

/**
 * order update action utils.
 */
public final class OrderUpdateActionUtils {

  /**
   * add lineItem.
   */
  public static final String ADD_LINE_ITEM = "addLineItem";

  /**
   * remove lineItem.
   */
  public static final String REMOVE_LINE_ITEM = "removeLineItem";

  /**
   * set lineItem quantity.
   */
  public static final String SET_LINE_ITEM_QUANTITY = "setLineItemQuantity";

  /**
   * set shipping address.
   */
  public static final String SET_SHIPPING_ADDRESS = "setShippingAddress";

  /**
   * set shipping address.
   */
  public static final String SET_BILLING_ADDRESS = "setBillingAddress";

  /**
   * default private constructor.
   */
  private OrderUpdateActionUtils() {
  }
}
