package io.reactivesw.order.application.service;

import io.reactivesw.message.client.consumer.Consumer;
import io.reactivesw.message.client.core.DefaultConsumerFactory;
import io.reactivesw.message.client.core.Message;
import io.reactivesw.message.client.utils.serializer.JsonDeserializer;
import io.reactivesw.order.application.model.event.OrderReservedEvent;
import io.reactivesw.order.domain.model.Order;
import io.reactivesw.order.domain.service.OrderService;
import io.reactivesw.order.infrastructure.configuration.EventConfig;
import io.reactivesw.order.infrastructure.enums.OrderStatus;
import io.reactivesw.order.infrastructure.util.EventSubscribers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Order reserved event consumer.
 */
@Service
public class OrderReservedConsumer {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(OrderReservedConsumer.class);

  /**
   * Message consumer.
   */
  private transient Consumer consumer;

  /**
   * Order service.
   */
  @Autowired
  private transient OrderService orderService;

  /**
   * Json deserializer.
   */
  private transient JsonDeserializer<OrderReservedEvent> jsonDeserializer;

  /**
   * Default constructor.
   */
  @Autowired
  public OrderReservedConsumer(EventConfig eventConfig) {
    consumer = DefaultConsumerFactory.createGoogleConsumer(eventConfig.getGoogleCloudProjectId(),
        EventSubscribers.ORDER_INVENTORY_RESERVED);
    jsonDeserializer = new JsonDeserializer(OrderReservedEvent.class);
  }

  /**
   * Executor.
   * Executes each 200 ms.
   */
  @Scheduled(fixedRate = 200)
  public void executor() {

    // Pull messages todo this should be configurable.
    List<Message> events = consumer.pullMessages(10);

    events.stream().forEach(
        message -> {
          OrderReservedEvent event = jsonDeserializer.deserialize(message.getData().toString());
          setOrderStatus(event.getOrderId(), event.isResult());
          consumer.acknowledgeMessage(message.getExternalId());//for google we put ach
          LOG.debug("Processed message. messageId: {},  externalId: {}", message.getId(), message
              .getExternalId());
        }
    );

  }

  /**
   * Set order status.
   *
   * @param orderId String
   * @param result  boolean
   */
  private void setOrderStatus(String orderId, boolean result) {
    Order order = orderService.getById(orderId);

    //TODO, should be created only?
    if (order.getOrderStatus().equals(OrderStatus.Created)
        || order.getOrderStatus().equals(OrderStatus.Payed)) {
      if (result) {
        order.setOrderStatus(OrderStatus.Reserved);
      } else {
        order.setOrderStatus(OrderStatus.ReservedFailed);
      }
      LOG.debug("Set Order status. OrderId: {}, Status: {}.", orderId, order.getOrderStatus());
      orderService.save(order);
    }
  }


}
