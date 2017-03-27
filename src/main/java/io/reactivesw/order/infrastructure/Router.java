package io.reactivesw.order.infrastructure;


/**
 * The type Order router.
 */
public final class Router {
  /**
   * root url of order.
   */
  public static final String ORDER_ROOT = "/";

  /**
   * order id.
   */
  public static final String ORDER_ID = "orderId";

  /**
   * id pattern.
   */
  public static final String ID_PATTERN = "{" + ORDER_ID + "}";

  /**
   * get order by id.
   */
  public static final String ORDER_WITH_ID = ORDER_ROOT + ID_PATTERN;

  /**
   * The constant order_health_check.
   */
  public static final String HEALTH_CHECK = ORDER_ROOT + "health";
}
