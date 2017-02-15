package io.reactivesw.order.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.reactivesw.model.Reference;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Created by Davis on 16/11/17.
 */
@Data
public class SyncInfo {

  /**
   * Reference to a Channel.
   * Connection to particular synchronization destination.
   */
  private Reference channel;

  /**
   * Can be used to reference an external order instance, file etc.
   */
  private String externalId;


  /**
   * The Synced at.
   */
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime syncedAt;
}
