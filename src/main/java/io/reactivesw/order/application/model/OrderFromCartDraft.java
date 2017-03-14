package io.reactivesw.order.application.model;

import io.reactivesw.order.infrastructure.enums.PaymentState;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by umasuo on 16/11/17.
 */
@Data
public class OrderFromCartDraft {

  @NotNull
  @Size(min = 1)
  private String id;

  @NotNull
  @Min(0)
  private Integer version;

  private String orderNumber;

  private PaymentState paymentState;

  @NotNull
  @Size(min = 1)
  private String paymentMethodToken;
}
