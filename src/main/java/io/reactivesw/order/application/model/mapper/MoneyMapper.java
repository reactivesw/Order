package io.reactivesw.order.application.model.mapper;

import io.reactivesw.order.domain.model.value.Money;

public class MoneyMapper {

  public static Money modelToEntity(io.reactivesw.model.Money model) {
    Money entity = null;
    if (model != null) {
      entity = new Money();
      entity.setCentAmount(model.getCentAmount());
      entity.setCurrencyCode(model.getCurrencyCode());
    }
    return entity;
  }

  public static io.reactivesw.model.Money entityToModel(Money entity) {
    io.reactivesw.model.Money model = null;
    if (entity != null) {
      model = new io.reactivesw.model.Money();

      model.setCentAmount(entity.getCentAmount());
      model.setCurrencyCode(entity.getCurrencyCode());
    }
    return model;
  }

}
