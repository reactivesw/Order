package io.reactivesw.order.domain.model;

import io.reactivesw.order.domain.model.value.LineItem;
import io.reactivesw.order.domain.model.value.MoneyValue;
import io.reactivesw.order.domain.model.value.ShippingAddress;
import io.reactivesw.order.infrastructure.enums.OrderStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * order entity.
 */
@Entity
@Table(name = "orders")
@Data
@EqualsAndHashCode(callSuper = false)
@EntityListeners(AuditingEntityListener.class)
public class Order {

  /**
   * Id
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * The Created at.
   */
  @CreatedDate
  @Column(name = "created_at")
  protected ZonedDateTime createdAt;

  /**
   * The Last modified at.
   */
  @LastModifiedDate
  @Column(name = "last_modified_at")
  protected ZonedDateTime lastModifiedAt;

  /**
   * version.
   */
  @Column
  @Version
  private Integer version;

  /**
   * complete time.
   */
  @Column(name = "complete_at")
  private ZonedDateTime completedAt;

  /**
   * customer id.
   */
  @Column(name = "customer_id")
  private String customerId;

  /**
   * List build line items, snapshot.
   */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<LineItem> lineItems;

  /**
   * total price.
   */
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private MoneyValue totalPrice;

  /**
   * the shipping address.
   */
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private ShippingAddress shippingAddress;

  /**
   * A two-digit country code as per ↗ ISO 3166-1 alpha-2 . Used for product variant price
   * selection.
   */
  @Column
  private String country;

  /**
   * order state.
   */
  @Column
  private OrderStatus orderStatus;

  /**
   * payment id will be set when the order been placed.
   */
  @Column
  private String paymentId;

  /**
   * Order number is used to identify order for customer.
   */
  @Column(unique = true)
  private Long orderNumber;
}
