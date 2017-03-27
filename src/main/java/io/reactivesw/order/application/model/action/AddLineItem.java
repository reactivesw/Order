package io.reactivesw.order.application.model.action;

import io.reactivesw.order.domain.model.Order;
import io.reactivesw.order.infrastructure.update.UpdateAction;
import io.reactivesw.order.infrastructure.util.OrderUpdateActionUtils;
import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AddLineItem implements UpdateAction, Serializable {

  @NotNull
  private String productId;

  @NotNull
  private Integer variantId;

  @NotNull
  @Min(1)
  private Integer quantity;

  @Override
  public String getActionName() {
    return OrderUpdateActionUtils.ADD_LINE_ITEM;
  }
}
