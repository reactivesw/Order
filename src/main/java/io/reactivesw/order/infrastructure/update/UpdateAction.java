package io.reactivesw.order.infrastructure.update;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.reactivesw.order.application.model.action.AddLineItem;
import io.reactivesw.order.application.model.action.RemoveLineItem;
import io.reactivesw.order.application.model.action.SetLineItemQuantity;
import io.reactivesw.order.application.model.action.SetShippingAddress;
import io.reactivesw.order.infrastructure.util.OrderUpdateActionUtils;

/**
 * update action.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property =
    "action")
@JsonSubTypes( {
    @JsonSubTypes.Type(value = AddLineItem.class, name = OrderUpdateActionUtils.ADD_LINE_ITEM),
    @JsonSubTypes.Type(value = RemoveLineItem.class, name = OrderUpdateActionUtils
        .REMOVE_LINE_ITEM),
    @JsonSubTypes.Type(value = SetLineItemQuantity.class, name = OrderUpdateActionUtils
        .SET_LINE_ITEM_QUANTITY),
    @JsonSubTypes.Type(value = SetShippingAddress.class, name = OrderUpdateActionUtils
        .SET_SHIPPING_ADDRESS),
})
public interface UpdateAction {
  /**
   * get action name.
   *
   * @return
   */
  String getActionName();
}
