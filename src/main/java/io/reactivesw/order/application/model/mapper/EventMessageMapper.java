package io.reactivesw.order.application.model.mapper;

import io.reactivesw.message.client.utils.serializer.JsonSerializer;
import io.reactivesw.order.application.model.event.OrderCreatedEvent;
import io.reactivesw.order.domain.model.EventMessage;
import io.reactivesw.order.infrastructure.configuration.EventConfig;
import io.reactivesw.order.infrastructure.enums.EventStatus;


/**
 * Event message mapper.
 */
public final class EventMessageMapper {

  /**
   * Json serializer.
   */
  private transient static JsonSerializer jsonSerializer = new JsonSerializer();

  /**
   * Default private constructor.
   */
  private EventMessageMapper() {
    // do nothing.
  }

  /**
   * To entity.
   *
   * @param event OrderCreatedEvent
   * @return EventMessage
   */
  public static EventMessage build(OrderCreatedEvent event, EventConfig eventConfig) {
    EventMessage eventMessage = new EventMessage();

    eventMessage.setStatus(EventStatus.CREATED);
    eventMessage.setCreatedAt(System.currentTimeMillis());
    eventMessage.setVersion(eventConfig.getOrderCreatedVersion());
    eventMessage.setTopic(eventConfig.getOrderCreatedName());

    eventMessage.setData(jsonSerializer.serialize(event));

    return eventMessage;
  }

}
