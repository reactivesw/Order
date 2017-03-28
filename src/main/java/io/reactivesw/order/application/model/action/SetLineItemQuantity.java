package io.reactivesw.order.application.model.action;

import io.reactivesw.order.infrastructure.update.UpdateAction;
import io.reactivesw.order.infrastructure.util.OrderUpdateActionUtils;
import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * set line item quantity.
 */
@Data
public class SetLineItemQuantity implements UpdateAction, Serializable {

  /**
   * auto generated serial version.
   */
  private static final long serialVersionUID = 4768572478463810178L;

  /**
   * line item id.
   * required.
   */
  @NotNull
  private String lineItemId;

  /**
   * quantity.
   * required.
   * the minimum is 1.
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
    return OrderUpdateActionUtils.SET_LINE_ITEM_QUANTITY;
  }
}
