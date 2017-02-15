package io.reactivesw.order.application.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@ApiModel
@Data
public class DeliveryItem {

  @ApiModelProperty(value = "ID of a LineItem or a CustomLineItem", required = true)
  private String id;

  @ApiModelProperty(required = true)
  private Integer quantity;
}
