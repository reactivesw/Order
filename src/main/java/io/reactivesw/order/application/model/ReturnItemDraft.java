package io.reactivesw.order.application.model;

import io.reactivesw.order.infrastructure.enums.ReturnShipmentState;

/**
 * The ReturnItemDraft needs to be given with the Add ReturnInfo update method. At this point only
 * Advised or Returned ReturnShipmentState is allowed. Item with Advised shipment state gets the
 * NonRefundable ReturnPaymentState and item with Returned shipment state gets Initial payment
 * state.
 * Created by umasuo on 16/11/17.
 */
public class ReturnItemDraft {

  private Integer quantity;

  private String lineItemId;

  private String comment;

  private ReturnShipmentState shipmentState;

}
