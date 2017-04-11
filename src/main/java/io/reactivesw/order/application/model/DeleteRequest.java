package io.reactivesw.order.application.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Delete request.
 */
@Data
public class DeleteRequest {

  /**
   * version.
   */
  @NotNull
  private Integer version;
}
