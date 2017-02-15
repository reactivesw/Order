package io.reactivesw.order.domain.model.value;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by umasuo on 16/11/17.
 */
@Entity
@Table(name = "tax_portion")
@Data
@EqualsAndHashCode(callSuper = false)
public class TaxPortionValue {
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
   * rate.
   */
  @Column
  private Float rate;

  /**
   * amount of money.
   */
  @OneToOne
  private MoneyValue amount;
}
