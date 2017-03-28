package io.reactivesw.order.application.model;

import io.reactivesw.model.Money;
import lombok.Data;

/**
 * price view used to receive data from product service.
 */
@Data
public class PriceView {

  /**
   * uuid.
   */
  private String id;

  /**
   * value.
   */
  private Money value;

}
