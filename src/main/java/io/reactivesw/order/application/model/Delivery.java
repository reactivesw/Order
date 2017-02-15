package io.reactivesw.order.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by umasuo on 16/11/17.
 */
@ApiModel(description = "Deliveries are compilations of information on how the articles are being" +
    " shipped to the customer. " +
    "A delivery can contain multiple items. " +
    "All items in a delivery can be shipped with several parcels. " +
    "To create a delivery, it is necessary to have a shipment method assigned to the order. " +
    "A sample use case for a delivery object is to create a delivery note.")
@Data
public class Delivery {

  @ApiModelProperty(required = true)
  private String id;

  @ApiModelProperty(required = true)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

  @ApiModelProperty(value = "Array of DeliveryItem, Items which are shipped in this delivery.",
      required = true)
  private List<DeliveryItem> items;

  @ApiModelProperty(required = false)
  private List<Parcel> parcels;

}
