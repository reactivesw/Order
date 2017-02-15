package io.reactivesw.order.application.model.mapper;

import io.reactivesw.model.Money;
import io.reactivesw.order.domain.model.value.MoneyValue;

/**
 * Created by umasuo on 16/12/8.
 */
public class MoneyMapper {

  public static MoneyValue modelToEntity(Money model) {
    MoneyValue entity = null;
    if (model != null) {
      entity = new MoneyValue();
      entity.setCentAmount(model.getCentAmount());
      entity.setCurrencyCode(model.getCurrencyCode());
    }
    return entity;
  }

  public static Money entityToModel(MoneyValue entity) {
    Money model = null;
    if (entity != null) {
      model = new Money();

      model.setCentAmount(entity.getCentAmount());
      model.setCurrencyCode(entity.getCurrencyCode());
    }
    return model;
  }

}
