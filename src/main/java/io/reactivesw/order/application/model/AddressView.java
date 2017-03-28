package io.reactivesw.order.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Address view.
 */
@Data
public class AddressView implements Serializable {

  /**
   * auto generated serial version.
   */
  private static final long serialVersionUID = 8783992306926856500L;

  /**
   * id.
   */
  private String id;

  /**
   * created time.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

  /**
   * last update time.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime lastModifiedAt;

  /**
   * human readable name
   */
  private String fullName;

  /**
   * zip code
   */
  private String zip;

  /**
   * phone
   */
  private String phone;

  /**
   * first line.
   */
  private String firstLine;

  /**
   * second line.
   */
  private String secondLine;

  /**
   * country.
   */
  private String country;

  /**
   * state.
   */
  private String state;

  /**
   * city.
   */
  protected String city;
}
