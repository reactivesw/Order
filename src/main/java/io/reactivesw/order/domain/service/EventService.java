package io.reactivesw.order.domain.service;

import io.reactivesw.order.application.model.event.OrderCreatedEvent;
import io.reactivesw.order.application.model.mapper.EventMessageMapper;
import io.reactivesw.order.application.model.mapper.OrderCreatedEventMapper;
import io.reactivesw.order.domain.model.EventMessage;
import io.reactivesw.order.domain.model.Order;
import io.reactivesw.order.infrastructure.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Event service.
 */
@Service
public class EventService {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(EventService.class);

  /**
   * The event repository.
   */
  @Autowired
  private transient EventRepository eventRepository;

  /**
   * Create Message with order and credit card id.
   *
   * @param order        Order
   * @param creditCardId String
   * @return Event message
   */
  public EventMessage createMessage(Order order, String creditCardId) {
    LOG.debug("Enter. OrderId: {}, creditCardId: {}.", order, creditCardId);

    OrderCreatedEvent event = OrderCreatedEventMapper.build(order, creditCardId);
    EventMessage message = EventMessageMapper.build(event);
    EventMessage savedMsg = eventRepository.save(message);

    LOG.debug("Exit. Message: {}.", savedMsg);
    return savedMsg;
  }
}
