package io.reactivesw.order.application.model;


import java.util.List;

/**
 * Created by Davis on 16/11/17.
 */
public class ProductVariantImportDraft {
  /**
   * The sequential ID of the variant within the product.
   * The variant with provided ID should exist in some existing product,
   * so you also need to specify the productId if this property is set,
   * or alternatively you can just specify SKU of the product variant.
   */
  private Integer id;

  /**
   * The SKU of the existing variant.
   */
  private String sku;

  /**
   * Array of Price.
   * The prices of the variant.
   * The prices should not contain two prices for the same price scope
   * (same currency, country and customer group).
   * If this property is defined,
   * then it will override the prices property from the original product variant,
   * otherwise prices property from the original product variant would be copied
   * in the resulting order.
   * Optional.
   */
  private List<PriceView> prices;

  /**
   * Array of AttributeEntity.
   * If this property is defined,
   * then it will override the attributes property from the original product variant,
   * otherwise attributes property from the original product variant
   * would be copied in the resulting order.
   * Optional.
   */
  private List<AttributeView> attributes;

  /**
   * Array of Image.
   * If this property is defined,
   * then it will override the images property from the original product variant,
   * otherwise images property from the original product variant
   * would be copied in the resulting order.
   * Optional.
   */
  private List<ImageView> images;

}
