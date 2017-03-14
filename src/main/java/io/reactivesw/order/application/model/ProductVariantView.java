package io.reactivesw.order.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.nio.file.attribute.AttributeView;
import java.util.List;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVariantView {

  private Integer id;

  private String sku;

  private String key;

  private List<PriceView> prices;

  private List<AttributeView> attributes;

  private PriceView price;

  private List<ImageView> images;

  private List<AssetView> assets;

}
