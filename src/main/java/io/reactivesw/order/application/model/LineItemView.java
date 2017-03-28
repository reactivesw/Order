package io.reactivesw.order.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.reactivesw.model.LocalizedString;
import io.reactivesw.model.Money;
import lombok.Data;

import java.util.List;

/**
 * line item view.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LineItemView {

  /**
   * uuid.
   */
  private String id;

  /**
   * product id.
   */
  private String productId;

  /**
   * variant id.
   */
  private Integer variantId;

  /**
   * localized string build the product.
   */
  private LocalizedString name;

  /**
   * sku.
   */
  private String sku;

  /**
   * list build images.
   */
  private List<ImageView> images;

  /**
   * single line item price.
   */
  private Money price;

  /**
   * total price.
   */
  private Money totalPrice;

  /**
   * quantity.
   */
  private Integer quantity;
}
