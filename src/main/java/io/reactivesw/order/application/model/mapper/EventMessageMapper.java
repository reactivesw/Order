package io.reactivesw.order.application.model.mapper;

import io.reactivesw.message.client.utils.serializer.JsonSerializer;
import io.reactivesw.order.application.model.event.OrderCreatedEvent;
import io.reactivesw.order.domain.model.EventMessage;
import io.reactivesw.order.infrastructure.enums.EventStatus;
import io.reactivesw.order.infrastructure.util.EventTopics;


/**
 * Event message mapper.
 */
public final class EventMessageMapper {

  /**
   * json serializer.
   */
  private transient static JsonSerializer jsonSerializer = new JsonSerializer();

  /**
   * default private constructor.
   */
  private EventMessageMapper() {
    // do nothing.
  }

  /**
   * to entity.
   *
   * @param event
   * @return EventMessage
   */
  public static EventMessage toEntity(OrderCreatedEvent event) {
    EventMessage eventMessage = new EventMessage();

    eventMessage.setStatus(EventStatus.CREATED);
    eventMessage.setCreatedAt(System.currentTimeMillis());
    eventMessage.setVersion(1);//TODO use config
    eventMessage.setTopic(EventTopics.ORDER_CREATED);

    eventMessage.setData(jsonSerializer.serialize(event));

    return eventMessage;
  }

}
