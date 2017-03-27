package io.reactivesw.order.application.model.action;

import io.reactivesw.order.infrastructure.update.UpdateAction;
import io.reactivesw.order.infrastructure.util.OrderUpdateActionUtils;
import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@Data
public class SetShippingAddress implements UpdateAction, Serializable {

  @NotNull
  private String addressId;

  @Override
  public String getActionName() {
    return OrderUpdateActionUtils.SET_SHIPPING_ADDRESS;
  }
}
