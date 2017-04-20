package io.reactivesw.order.domain.service;

import io.reactivesw.order.application.model.event.OrderCreatedEvent;
import io.reactivesw.order.application.model.mapper.EventMessageMapper;
import io.reactivesw.order.application.model.mapper.OrderCreatedEventMapper;
import io.reactivesw.order.domain.model.EventMessage;
import io.reactivesw.order.domain.model.Order;
import io.reactivesw.order.infrastructure.enums.EventStatus;
import io.reactivesw.order.infrastructure.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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


  /**
   * Get events from db, and set their status to PENDING.
   *
   * @return List of Event
   */
  @Transactional
  public List<EventMessage> getEvents() {

    //fetch the first page and fetch 10 event each time. TODO change this to configuration.
    Page<EventMessage> page = eventRepository.findAll(isAvailable(), new PageRequest(0, 10));
    List<EventMessage> events = page.getContent();

    LOG.debug("Fetch events. eventCount: {}, countInDb: {}.", events.size(), page
        .getTotalElements());

    events.stream().forEach(
        event -> {
          if (event.getStatus() == EventStatus.CREATED) {
            //if the event is in "CREATED" status, then change it to "PENDING"
            event.setStatus(EventStatus.PENDING);
            eventRepository.save(events);
          }
        }
    );

    // Log the event data.
    LOG.trace("Fetch events. events: {}", Arrays.toString(events.toArray()));
    return events;
  }


  /**
   * Specification for fetch Events.
   *
   * @return Specification
   */
  public static Specification<EventMessage> isAvailable() {
    return new Specification<EventMessage>() {
      /**
       * Predicate builder.
       */
      public Predicate toPredicate(Root<EventMessage> root, CriteriaQuery<?> query,
                                   CriteriaBuilder builder) {
        // Fetch events for two kind of conditions.
        return builder.or(
            builder.and(
                // Condition1: Fetch event whose status is "PENDING", but created 1 minutes ago.
                builder.lessThan(root.get("createdAt"), System.currentTimeMillis() - 60000),
                builder.equal(root.get("status"), EventStatus.PENDING.getValue())
            ),
            // Condition2: Fetch event whose status is "CREATED"
            builder.equal(root.get("status"), EventStatus.CREATED.getValue())
        );
      }
    };
  }

}
