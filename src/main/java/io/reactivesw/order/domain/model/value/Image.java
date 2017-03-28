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
 * image entity.
 */
@Entity
@Table(name = "image")
@Data
@EqualsAndHashCode(callSuper = false)
public class Image {

  /**
   * Id
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  protected String id;

  /**
   * image url.
   */
  @Column(name = "url")
  private String url;

  /**
   * label.
   */
  @Column(name = "label")
  private String label;

}
