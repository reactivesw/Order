package io.reactivesw.order.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.reactivesw.model.Money;
import io.reactivesw.model.Reference;
import io.reactivesw.order.infrastructure.enums.InventoryMode;
import io.reactivesw.order.infrastructure.enums.OrderState;
import io.reactivesw.order.infrastructure.enums.PaymentState;
import io.reactivesw.order.infrastructure.enums.ShipmentState;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by Davis on 16/11/17.
 */
@Data
public class OrderImportDraft {

  /**
   * String that unique identifies an order.
   * It can be used to create more human-readable (in contrast to ID) identifier for the order.
   * It should be unique within a merchant.
   */
  private String orderNumber;

  /**
   * The Customer id.
   */
  private String customerId;

  /**
   * The Customer email.
   */
  private String customerEmail;

  /**
   * Array of LineItemImportDraft.
   * If not given customLineItems must not be empty.
   * Optional.
   */
  private List<LineItemImportDraft> lineItems;

  /**
   * Array of CustomLineItem.
   * If not given lineItems must not be empty.
   * Optional.
   */
//  private List<CustomLineItem> customLineItems;

  /**
   * The Total price.
   */
  private Money totalPrice;

  /**
   * The Shipping address.
   */
  private AddressView shippingAddress;

  /**
   * The Billing address.
   */
  private AddressView billingAddress;

  /**
   * Reference to a CustomerGroup.
   * Set when the customer is set and the customer is a member of a customer group.
   * Used for product variant price selection.
   * Optional.
   */
  private Reference customerGroup;

  /**
   * A two-digit country code as per ↗ ISO 3166-1 alpha-2 .
   * Used for product variant price selection.
   */
  private String country;

  /**
   * If not given the Open state will be assigned by default.
   */
  private OrderState orderState;

  /**
   * The Shipment state.
   */
  private ShipmentState shipmentState;

  /**
   * The Payment state.
   */
  private PaymentState paymentState;

  /**
   * Set if the ShippingMethod is set.
   */
  private ShippingInfoView shippingInfo;

  /**
   * The Completed at.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime completedAt;

  /**
   * If not given the mode None will be assigned by default.
   */
  private InventoryMode inventoryMode;
}
