package io.reactivesw.order.application.model;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.model.Reference;

/**
 * Created by Davis on 16/11/17.
 */
public class LineItemImportDraft {

  /**
   * ID of the existing product.
   * You also need to specify the ID of the variant if this property is set or alternatively
   * you can just specify SKU of the product variant.
   */
  private String productId;

  /**
   * The product name.
   */
  private LocalizedString name;

  /**
   * The Variant.
   */
  private ProductVariantImportDraft variant;

  /**
   * The Price.
   */
  private PriceView price;

  /**
   * The Quantity.
   */
  private Integer quantity;

  /**
   * Reference to a Channel.
   * Optional connection to particular supplier.
   * By providing supply channel information,
   * you can unique identify inventory entries that should be reserved.
   * Provided channel should have role InventorySupply.
   * Optional.
   */
  private Reference supplyChannel;

}
