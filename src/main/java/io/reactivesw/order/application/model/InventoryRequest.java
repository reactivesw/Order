package io.reactivesw.order.application.model;

import lombok.Data;

/**
 * inventory request.
 */
@Data
public class InventoryRequest {
  /**
   * sku name.
   */
  private String skuName;

  /**
   * quantity to reduce.
   */
  private Integer quantity;
}
