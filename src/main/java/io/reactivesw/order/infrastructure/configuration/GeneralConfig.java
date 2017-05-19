package io.reactivesw.order.infrastructure.configuration;

/**
 * General configuration for order.
 */
public class GeneralConfig {

  /**
   * Maximum time to retry generate orderNumber after failed.
   */
  public static final int GENERATE_ORDER_NUMBER_MAX_TRIES = 5;
}
