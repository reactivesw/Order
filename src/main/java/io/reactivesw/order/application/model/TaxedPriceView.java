package io.reactivesw.order.application.model;

import io.reactivesw.model.Money;

import lombok.Data;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class TaxedPriceView {

  private Money totalNet;

  private Money totalGross;

}
