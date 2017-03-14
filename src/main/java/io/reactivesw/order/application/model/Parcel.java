package io.reactivesw.order.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class Parcel {

  private String id;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

  private ParcelMeasurements measurements;

  private TrackingData trackingData;

}
