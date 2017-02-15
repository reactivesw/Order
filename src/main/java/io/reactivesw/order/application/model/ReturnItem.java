package io.reactivesw.order.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.reactivesw.order.infrastructure.enums.ReturnPaymentState;
import io.reactivesw.order.infrastructure.enums.ReturnShipmentState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Created by umasuo on 16/11/17.
 */
@ApiModel
@Data
public class ReturnItem {

  @ApiModelProperty(required = true)
  private String id;

  @ApiModelProperty(required = true)
  private Integer quantity;

  @ApiModelProperty(required = true)
  private String lineItemId;

  @ApiModelProperty(required = true)
  private String comment;

  @ApiModelProperty(required = true)
  private ReturnShipmentState shipmentState;

  @ApiModelProperty(required = true)
  private ReturnPaymentState paymentState;

  @ApiModelProperty(required = true)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime lastModifiedAt;

  @ApiModelProperty(required = true)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

}
