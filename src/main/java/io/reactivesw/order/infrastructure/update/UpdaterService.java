package io.reactivesw.order.infrastructure.update;

import io.reactivesw.model.Updater;
import io.reactivesw.order.domain.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UpdaterService implements Updater<Order, UpdateAction> {

  /**
   * ImmutableMap for discount code update mapper.
   */
  Map<Class<?>, Updater> updateMappers = new ConcurrentHashMap<>();

  /**
   * ApplicationContext for get update services.
   */
  @Autowired
  private transient ApplicationContext context;

  /**
   * put the value in action to entity.
   *
   * @param entity E
   * @param action UpdateAction
   */
  @Override
  public void handle(Order entity, UpdateAction action) {
    Updater updater = getUpdateService(action);
    updater.handle(entity, action);
  }

  /**
   * get mapper.
   *
   * @param action UpdateAction
   * @return ZoneUpdateMapper
   */
  private Updater getUpdateService(UpdateAction action) {
    Updater updater = updateMappers.get(action.getActionName());
    if (updater == null) {
      updater = (Updater) context.getBean(action.getActionName());
    }
    return updater;
  }

}
