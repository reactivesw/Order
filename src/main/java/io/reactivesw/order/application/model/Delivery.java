package io.reactivesw.order.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class Delivery {

  private String id;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

  private List<DeliveryItem> items;

  private List<Parcel> parcels;

}
