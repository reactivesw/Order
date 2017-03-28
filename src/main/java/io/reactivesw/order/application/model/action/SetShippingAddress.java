package io.reactivesw.order.application.model.action;

import io.reactivesw.order.infrastructure.update.UpdateAction;
import io.reactivesw.order.infrastructure.util.OrderUpdateActionUtils;
import lombok.Data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * set shipping address action.
 */
@Data
public class SetShippingAddress implements UpdateAction, Serializable {

  /**
   * auto generated serial version.
   */
  private static final long serialVersionUID = 2204547031188565983L;

  /**
   * address id.
   */
  @NotNull
  private String addressId;

  /**
   * get action name.
   *
   * @return
   */
  @Override
  public String getActionName() {
    return OrderUpdateActionUtils.SET_SHIPPING_ADDRESS;
  }
}
