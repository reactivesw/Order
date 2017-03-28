package io.reactivesw.order.application.model.mapper;

import io.reactivesw.model.Money;
import io.reactivesw.order.domain.model.value.MoneyValue;

/**
 * money mapper.
 */
public final class MoneyMapper {

  /**
   * private default constructor.
   */
  private MoneyMapper() {
  }

  /**
   * to entity.
   *
   * @param model
   * @return
   */
  public static MoneyValue toEntity(Money model) {
    MoneyValue entity = null;
    if (model != null) {
      entity = new MoneyValue();
      entity.setCentAmount(model.getCentAmount());
      entity.setCurrencyCode(model.getCurrencyCode());
    }
    return entity;
  }

  /**
   * to view.
   *
   * @param entity
   * @return
   */
  public static Money toView(MoneyValue entity) {
    Money model = null;
    if (entity != null) {
      model = new Money();
      model.setCentAmount(entity.getCentAmount());
      model.setCurrencyCode(entity.getCurrencyCode());
    }
    return model;
  }

}
