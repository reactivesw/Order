package io.reactivesw.order.domain.model.value;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by umasuo on 16/11/28.
 */
@Entity
@Table(name = "line_item")
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class LineItem {
  /**
   * Id
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * product id.
   */
  @Column(name = "product_id")
  private String productId;

  /**
   * name in localized string.
   */
  @Column
  private LocalizedStringValue name;

  /**
   * id in number.
   */
  @Column(nullable = false)
  private Integer variantId;

  /**
   * sku.
   */
  @Column(nullable = false)
  private String sku;
  /**
   * list of images.
   */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Image> images;

  /**
   * price.
   */
  @OneToOne
  private MoneyValue price;

  /**
   * The total price of this line item. If the line item is discounted, then the totalPrice is
   * the DiscountedLineItemPriceForQuantity multiplied by quantity. Otherwise the total price is
   * the product price multiplied by the quantity. totalPrice may or may not include the taxes:
   * it depends on the taxRate.includedInPrice property.
   */
  @OneToOne
  private MoneyValue totalPrice;

  /**
   * quantity.
   */
  @Column
  private Integer quantity;

}
