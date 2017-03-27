package io.reactivesw.order.application.model;

import io.reactivesw.model.Money;
import lombok.Data;

@Data
public class PriceView {

  private String id;

  private Money value;

}
