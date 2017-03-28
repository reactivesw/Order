package io.reactivesw.order.application.model.action;

import io.reactivesw.order.infrastructure.update.UpdateAction;
import io.reactivesw.order.infrastructure.util.OrderUpdateActionUtils;
import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * add line item action.
 */
@Data
public class AddLineItem implements UpdateAction, Serializable {

  /**
   * auto generated serial version.
   */
  private static final long serialVersionUID = -9117292971999138039L;

  /**
   * product id, can not be null.
   */
  @NotNull
  private String productId;

  /**
   * variant id, can not be null.
   */
  @NotNull
  private Integer variantId;

  /**
   * quantity, minimum value is 1.
   */
  @NotNull
  @Min(1)
  private Integer quantity;

  /**
   * get action name.
   *
   * @return
   */
  @Override
  public String getActionName() {
    return OrderUpdateActionUtils.ADD_LINE_ITEM;
  }
}
