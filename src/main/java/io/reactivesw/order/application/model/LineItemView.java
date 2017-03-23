package io.reactivesw.order.application.model;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.model.Money;
import lombok.Data;

@Data
public class LineItemView {

  private String id;

  private String productId;

  private Integer variantId;

  private LocalizedString name;

  private String slug;

  private PriceView price;

  private Money totalPrice;

  private Integer quantity;
}
