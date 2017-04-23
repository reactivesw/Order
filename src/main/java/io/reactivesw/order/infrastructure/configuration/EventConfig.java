package io.reactivesw.order.infrastructure.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Event config.
 */
@Configuration
@Data
public class EventConfig {

  /**
   * Google cloud project id.
   */
  @Value("${io.reactivesw.message.google.project.id}")
  private String googleCloudProjectId;

  /**
   * Order created event topic name.
   */
  @Value("${io.reactivesw.message.topic.ordercreated.name}")
  private String orderCreatedName;

  /**
   * Order created event topic version.
   */
  @Value("${io.reactivesw.message.topic.ordercreated.version}")
  private Integer orderCreatedVersion;

  /**
   * Order payed event version.
   */
  @Value("${io.reactivesw.message.topic.orderpayed.subscriber}")
  private String orderPayedSubscriber;

  /**
   * Order reserved event version.
   */
  @Value("${io.reactivesw.message.topic.orderreserved.subscriber}")
  private String orderReservedSubscriber;
}
