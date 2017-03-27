package io.reactivesw.order.application.model.mapper;

import io.reactivesw.order.domain.model.value.MoneyValue;

public class MoneyMapper {

  public static MoneyValue modelToEntity(io.reactivesw.model.Money model) {
    MoneyValue entity = null;
    if (model != null) {
      entity = new MoneyValue();
      entity.setCentAmount(model.getCentAmount());
      entity.setCurrencyCode(model.getCurrencyCode());
    }
    return entity;
  }

  public static io.reactivesw.model.Money entityToModel(MoneyValue entity) {
    io.reactivesw.model.Money model = null;
    if (entity != null) {
      model = new io.reactivesw.model.Money();

      model.setCentAmount(entity.getCentAmount());
      model.setCurrencyCode(entity.getCurrencyCode());
    }
    return model;
  }

}
