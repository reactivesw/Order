package io.reactivesw.order.application.model;

import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class TrackingData {

  private String trackingId;

  private String carrier;

  private String provider;

  private String providerTransaction;

  private Boolean isReturn;

}
