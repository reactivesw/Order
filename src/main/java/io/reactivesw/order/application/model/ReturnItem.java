package io.reactivesw.order.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.reactivesw.order.infrastructure.enums.ReturnPaymentState;
import io.reactivesw.order.infrastructure.enums.ReturnShipmentState;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class ReturnItem {

  private String id;

  private Integer quantity;

  private String lineItemId;

  private String comment;

  private ReturnShipmentState shipmentState;

  private ReturnPaymentState paymentState;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime lastModifiedAt;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

}
