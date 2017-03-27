package io.reactivesw.order.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.reactivesw.model.LocalizedString;
import io.reactivesw.model.Money;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LineItemView {

  private String id;

  private String productId;

  private Integer variantId;

  private LocalizedString name;

  private String sku;

  private List<ImageView> images;

  private Money price;

  private Money totalPrice;

  private Integer quantity;
}
