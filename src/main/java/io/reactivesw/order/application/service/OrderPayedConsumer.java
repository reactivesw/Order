package io.reactivesw.order.application.service;

import io.reactivesw.message.client.consumer.Consumer;
import io.reactivesw.message.client.core.DefaultConsumerFactory;
import io.reactivesw.message.client.core.Message;
import io.reactivesw.message.client.utils.serializer.JsonDeserializer;
import io.reactivesw.order.application.model.event.OrderPayedEvent;
import io.reactivesw.order.domain.model.Order;
import io.reactivesw.order.domain.service.OrderService;
import io.reactivesw.order.infrastructure.configuration.EventConfig;
import io.reactivesw.order.infrastructure.enums.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Order payed event consumer.
 */
@Service
public class OrderPayedConsumer {

  /**
   * Logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(OrderPayedConsumer.class);

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
  private transient JsonDeserializer<OrderPayedEvent> jsonDeserializer;

  /**
   * Default constructor.
   */
  @Autowired
  public OrderPayedConsumer(EventConfig eventConfig) {
    consumer = DefaultConsumerFactory.createGoogleConsumer(eventConfig.getGoogleCloudProjectId(),
        eventConfig.getOrderPayedSubscriber());
    jsonDeserializer = new JsonDeserializer(OrderPayedEvent.class);
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
          OrderPayedEvent event = jsonDeserializer.deserialize(message.getData().toString());
          setOrderStatus(event.getOrderId(), event.getPaymentId(), event.isResult());
          consumer.acknowledgeMessage(message.getExternalId());//for google we put ach
          LOG.debug("Processed message. messageId: {},  externalId: {}", message.getId(), message
              .getExternalId());
        }
    );

  }

  /**
   * Set order status.
   *
   * @param orderId   String
   * @param paymentId String
   * @param result boolean
   */
  private void setOrderStatus(String orderId, String paymentId, boolean result) {
    Order order = orderService.getById(orderId);

    //TODO, should be reserved only?
    if (order.getOrderStatus().equals(OrderStatus.Reserved) || order.getOrderStatus().equals
        (OrderStatus.Created)) {
      if (result) {
        order.setOrderStatus(OrderStatus.Payed);
      } else {
        order.setOrderStatus(OrderStatus.PayFailed);
      }
      LOG.debug("Set Order status. OrderId: {}, paymentId: {}, Status: {}.", orderId, paymentId,
          order.getOrderStatus());
      orderService.save(order);
    }
  }


}
