package io.reactivesw.order.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * image view.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageView implements Serializable {
  /**
   * auto generated serial version.
   */
  private static final long serialVersionUID = 8149280114568821925L;
  
  /**
   * URL of the image in its original size.
   * This can be used to obtain the image in different sizes.
   */
  private String url;

  /**
   * Custom label that can be used, for example, as an image description.
   */
  private String label;
}
