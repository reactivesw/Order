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
import javax.persistence.Table;

/**
 * Created by umasuo on 16/11/17.
 * //TODO should we keep this in snapshot?
 */
@Entity
@Table(name = "tax_rate")
@Data
@EqualsAndHashCode(callSuper = false)
public class TaxRateValue {

  /**
   * Id
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * name.
   */
  @Column
  private String name;

  /**
   * amount.
   */
  @Column
  private Float amount;

  /**
   * if included in price.
   */
  @Column
  private Boolean includedInPrice;

  /**
   * country.
   */
  @Column
  private String country;

  /**
   * state.
   */
  @Column
  private String state;

  /**
   * list of sub rate.
   */
  @OneToMany
  private Set<SubRateValue> subRates;
}
