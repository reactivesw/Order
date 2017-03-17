package io.reactivesw.order.domain.model.value;

import io.reactivesw.database.dialect.JSONBUserType;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by umasuo on 16/11/28.
 */
@Entity
@Table(name = "shipping_info")
@Data
@EqualsAndHashCode(callSuper = false)
@TypeDef(name = "List", typeClass = JSONBUserType.class, parameters = {
    @Parameter(name = JSONBUserType.CLASS, value = "java.util.List")}
)
public class ShippingInfoValue {
  /**
   * Id
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * shipping method name.
   */
  @Column(name = "shipping_method_name")
  private String shippingMethodName;

  /**
   * price.
   * Determined based on the ShippingRate and address.
   */
  @OneToOne
  private MoneyValue price;

  /**
   * shipping rate.
   */
  @OneToOne
  private ShippingRateValue shippingRate;

  /**
   * Set once the taxRate is set.
   */
  @OneToOne
  private TaxedItemPriceValue taxedPrice;

  /**
   * Will be set automatically in the Platform TaxMode once the shipping address is set is set. For
   * the External tax mode the tax rate has to be set explicitly with the ExternalTaxRateDraft.
   */
  @OneToOne
  private TaxRateValue taxRate;

  /**
   * tax category id.
   */
  @Column(name = "tax_category")
  private String taxCategory;

  /**
   * shipping method.
   */
  @Column(name = "shipping_method_id")
  private String shippingMethod;

  /**
   * delivery ids.
   */
  @Type(type = "List")
  private List<String> deliveries;

  /**
   * DiscountedLineItemPrice id.
   */
  @Column
  private String discountedPrice;

}
