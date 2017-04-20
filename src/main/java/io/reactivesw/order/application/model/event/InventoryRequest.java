package io.reactivesw.order.application.model.event;

import lombok.Data;

/**
 * inventory request.
 */
@Data
public class InventoryRequest {
  /**
   * sku of the variant.
   */
  private String sku;

  /**
   * quantity.
   */
  private Integer quantity;
}
