package io.reactivesw.order.application.model;

import io.reactivesw.order.domain.model.value.AssetDimensionsView;

import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class AssetSourceView {

  private String uri;

  private String key;

  private AssetDimensionsView dimensions;

  private String contentType;

}
