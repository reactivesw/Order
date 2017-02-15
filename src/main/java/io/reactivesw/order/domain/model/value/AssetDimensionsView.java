package io.reactivesw.order.domain.model.value;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@ApiModel(description = "The width and height of the asset source.")
@Data
public class AssetDimensionsView {

  @ApiModelProperty(value = "The width of the asset source", required = true)
  private float w;

  @ApiModelProperty(value = "The height of the asset source", required = true)
  private float h;
}
