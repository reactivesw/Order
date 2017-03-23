package io.reactivesw.order.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.reactivesw.model.Money;
import io.reactivesw.order.infrastructure.enums.OrderState;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderView {

  /**
   * The unique ID of the order.
   */
  private String id;

  /**
   * The current version of the order.
   */
  private Integer version;

  /**
   * The Created at.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

  /**
   * The Last modified at.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime lastModifiedAt;

  /**
   * This field will only be present if it was set for Order Import
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime completedAt;

  /**
   * String that uniquely identifies an order.
   * It can be used to create more human-readable (in contrast to ID) identifier for the order.
   * It should be unique across a merchant. Once it’s set it cannot be changed.
   */
  private String orderNumber;

  /**
   * The Customer id.
   */
  private String customerId;

  /**
   * Identifies cart and order belonging to an anonymous session
   * (the customer has not signed up/in yet).
   */
  private String anonymousId;

  /**
   * Array of LineItem.
   */
  private List<LineItemView> lineItems;

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
   * A two-digit country code as per ISO 3166-1 alpha-2 . Used for product variant price selection.
   */
  private String country;

  /**
   * One of the four predefined OrderStates.
   */
  private OrderState orderState;

}
