package io.reactivesw.order.domain.model.value;

import io.reactivesw.database.dialect.JSONBUserType;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Davis on 16/11/23.
 */
@Entity
@Table(name = "image")
@Data
@EqualsAndHashCode(callSuper = false)
@TypeDef(name = "Dimensions", typeClass = JSONBUserType.class, parameters = {
    @Parameter(name = JSONBUserType.CLASS,
        value = "io.reactivesw.order.domain.model.value.AssetDimensionsView")}
)
public class ImageValue {
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
  @Type(type = "Dimensions")
  private AssetDimensionsView dimensions;

  /**
   * label.
   */
  @Column(name = "label")
  private String label;

}
