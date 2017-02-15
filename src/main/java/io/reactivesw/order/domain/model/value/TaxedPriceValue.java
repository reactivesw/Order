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
 * Created by umasuo on 16/11/17.
 */
@Entity
@Table(name = "taxed_price")
@Data
@EqualsAndHashCode(callSuper = false)
public class TaxedPriceValue {
  /**
   * Id
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * total net.
   */
  @OneToOne
  private MoneyValue totalNet;

  /**
   * total gross.
   */
  @OneToOne
  private MoneyValue totalGross;

  /**
   * list of tax portion.
   */
  @OneToMany
  private Set<TaxPortionValue> taxPortions;
}
