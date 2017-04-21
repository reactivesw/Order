package io.reactivesw.order.domain.model;

import io.reactivesw.order.infrastructure.enums.EventStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Event.
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "event")
public class EventMessage {

  /**
   * Uuid.
   */
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id")
  private String id;

  /**
   * The Created at.
   */
  @Column(name = "created_at")
  private long createdAt;

  /**
   * Sequence number.
   */
  private String sequenceNumber;

  /**
   * The event will be expired in expire.
   */
  private long expire;

  /**
   * Version.
   */
  private Integer version;

  /**
   * Json string payload.
   */
  @Column(length = 2048)
  private String data;

  /**
   * Topic.
   */
  private String topic;

  /**
   * Event status.
   */
  private EventStatus status;
}
