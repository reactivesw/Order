package io.reactivesw.order.domain.model.value;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * shipping address entity.
 */
@Entity
@Table(name = "shipping_address")
@Data
@EqualsAndHashCode(callSuper = false)
public class ShippingAddress {

  /**
   * uuid.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  private String id;

  /**
   * name of this address.
   */
  @Column
  private String fullName;

  /**
   * postal code.
   */
  @Column
  private String zip;

  /**
   * phone.
   */
  @Column
  private String phone;

  /**
   * additional address info.
   */
  @Column
  private String firstLine;

  /**
   * external id.
   */
  @Column
  private String secondLine;

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
   * city.
   */
  @Column
  protected String city;

}
