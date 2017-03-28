package io.reactivesw.order.application.model.action;

import io.reactivesw.order.infrastructure.update.UpdateAction;
import io.reactivesw.order.infrastructure.util.OrderUpdateActionUtils;
import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * remove line item from order.
 */
@Data
public class RemoveLineItem implements UpdateAction, Serializable {

  /**
   * auto generated serial version.
   */
  private static final long serialVersionUID = 3882818062009739796L;
  /**
   * line item id.
   */
  @NotNull
  private String lineItemId;

  /**
   * quantity.
   */
  private Integer quantity;

  /**
   * get action name.
   *
   * @return
   */
  @Override
  public String getActionName() {
    return OrderUpdateActionUtils.REMOVE_LINE_ITEM;
  }
}
