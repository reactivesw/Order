package io.reactivesw.order.application.service;


import io.reactivesw.message.client.core.DefaultProducerFactory;
import io.reactivesw.message.client.core.Message;
import io.reactivesw.message.client.producer.Producer;
import io.reactivesw.order.domain.model.EventMessage;
import io.reactivesw.order.domain.service.EventService;
import io.reactivesw.order.infrastructure.configuration.EventConfig;
import io.reactivesw.order.infrastructure.util.EventTopics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * EventMessage publisher.
 */
@Service
public class EventPublisher {

  /**
   * Logger.
   */
  private final static Logger LOG = LoggerFactory.getLogger(EventPublisher.class);

  /**
   * Event repository.
   */
  @Autowired
  private transient EventService eventService;

  /**
   * Producer map.
   */
  private transient final Map<String, Producer> producerMap = new ConcurrentHashMap<>();

  /**
   * Default constructor.
   */
  @Autowired
  public EventPublisher(EventConfig eventConfig) {
    Producer orderCreatedProducer = DefaultProducerFactory.createGoogleProducer(
        eventConfig.getGoogleCloudProjectId(), EventTopics.ORDER_CREATED);

    producerMap.put(EventTopics.ORDER_CREATED, orderCreatedProducer);
  }

  /**
   * Executor.
   * Executes each 200 ms.
   */
  @Scheduled(fixedRate = 200)
  public void executor() {

    List<EventMessage> events = eventService.getEvents();

    events.stream().forEach(
        event -> {
          Message message = Message.build(event.getId(), event.getSequenceNumber(),
              event.getCreatedAt(), event.getVersion(), event.getExpire(), event.getData());

          publishEvent(event.getTopic(), message);
        }
    );
  }

  /**
   * Publish an event to a topic.
   *
   * @param topicName topic name
   * @param message   event
   */
  private void publishEvent(String topicName, Message message) {

    Producer producer = producerMap.get(topicName);
    if (producer == null) {
      // The topic may be deleted or changed name.
      LOG.error("Producer not found. topicName: {}.", topicName);
    } else {
      LOG.debug("Publish event. topicName: {}, event: {}.", topicName, message);
      producer.publish(message);
    }
  }


}
