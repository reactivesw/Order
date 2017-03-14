package io.reactivesw.order.application.model;

import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class ParcelMeasurements {

  private Float heightInMillimeter;

  private Float lengthInMillimeter;

  private Float widthInMillimeter;

  private Float weightInGram;

}
