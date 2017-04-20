package io.reactivesw.order.application.model.event;

import lombok.Data;

/**
 * Inventory request.
 */
@Data
public class InventoryRequest {
  /**
   * Sku of the variant.
   */
  private String sku;

  /**
   * Quantity.
   */
  private Integer quantity;
}
