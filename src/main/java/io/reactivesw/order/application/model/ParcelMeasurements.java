package io.reactivesw.order.application.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@ApiModel(description = "Information regarding the dimensions of a parcel.")
@Data
public class ParcelMeasurements {

  @ApiModelProperty(required = true)
  private Float heightInMillimeter;

  @ApiModelProperty(required = true)
  private Float lengthInMillimeter;

  @ApiModelProperty(required = true)
  private Float widthInMillimeter;

  @ApiModelProperty(required = true)
  private Float weightInGram;

}
