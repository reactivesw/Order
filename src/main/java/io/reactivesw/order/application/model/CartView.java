package io.reactivesw.order.application.model;

import io.reactivesw.model.Money;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CartView implements Serializable {

  private String id;

  private Integer version;

  private String customerId;

  private List<LineItemView> lineItems;

  private Money totalPrice;

  private String country;

  private String currencyCode;
}
