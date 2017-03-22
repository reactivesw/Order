package io.reactivesw.order.domain.model.value;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by umasuo on 16/11/28.
 */
@Entity
@Table(name = "shipping_address")
@Data
@EqualsAndHashCode(callSuper = false)
public class ShippingAddressValue {

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

  @Column
  private String country;

  @Column
  private String state;

  @Column
  protected String city;

}
