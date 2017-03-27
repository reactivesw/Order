package io.reactivesw.order.domain.model;

import io.reactivesw.order.domain.model.value.BillingAddress;
import io.reactivesw.order.domain.model.value.LineItem;
import io.reactivesw.order.domain.model.value.MoneyValue;
import io.reactivesw.order.domain.model.value.ShippingAddress;
import io.reactivesw.order.infrastructure.enums.OrderState;
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

@Entity
@Table(name = "order")
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
   * String that uniquely identifies an order. It can be used to create more human-readable (in
   * contrast to ID) identifier for the order. It should be unique across a merchant. Once it’s
   * set it cannot be changed.
   */
  @Column(name = "order_name")
  private String orderName;

  /**
   * customer id.
   */
  @Column(name = "customer_id")
  private String customerId;

  /**
   * anonymous id.
   */
  @Column(name = "anonymous_id")
  private String anonymousId;

  /**
   * List of line items, snapshot.
   */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<LineItem> lineItems;

  /**
   * total price.
   */
  @OneToOne
  private MoneyValue totalPrice;

  /**
   * the shipping address.
   */
  @OneToOne
  private ShippingAddress shippingAddress;

  /**
   * the billing address.
   */
  @OneToOne
  private BillingAddress billingAddress;

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
  private OrderState orderState;

}
