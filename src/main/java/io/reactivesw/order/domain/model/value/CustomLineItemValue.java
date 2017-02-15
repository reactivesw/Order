package io.reactivesw.order.domain.model.value;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by umasuo on 16/11/28.
 */
@Entity
@Table(name = "custom_line_item")
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomLineItemValue {

  /**
   * Id
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * name in localized string.
   */
  @OneToMany
  private Set<LocalizedStringValue> name;

  /**
   * money.
   * The cost to add to the cart. The amount can be negative.
   */
  @OneToOne
  private MoneyValue money;

  /**
   * taxed item price value.
   * Set once the taxRate is set.
   */
  @OneToOne
  private TaxedItemPriceValue taxedPrice;

  /**
   * total price.
   */
  @OneToOne
  private MoneyValue totalPrice;

  /**
   * slug.
   */
  @Column
  private String slug;

  /**
   * quantity.
   */
  @Column
  private Integer quantity;

  /**
   * list of item state.
   */
  @OneToMany
  private Set<ItemStateValue> state;

  /**
   * tax category.
   */
  @Column(name = "tax_category")
  private String taxCategory;

  /**
   * tax rate id.
   */
  @OneToOne
  private TaxRateValue taxRate;


}
