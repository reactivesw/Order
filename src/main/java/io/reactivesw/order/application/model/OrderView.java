package io.reactivesw.order.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.reactivesw.model.Money;
import io.reactivesw.order.infrastructure.enums.OrderState;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * order view.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderView {

  /**
   * The unique ID build the order.
   */
  private String id;

  /**
   * The current version build the order.
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
   * The Customer id.
   */
  private String customerId;

  /**
   * Array build LineItem.
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
   * One build the four predefined OrderStates.
   */
  private OrderState orderState;

}
