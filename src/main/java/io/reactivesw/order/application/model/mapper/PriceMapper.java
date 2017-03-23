package io.reactivesw.order.application.model.mapper;

import io.reactivesw.order.application.model.PriceView;
import io.reactivesw.order.domain.model.value.Price;

public final class PriceMapper {
  /**
   * Instantiates a new Price mapper.
   */
  private PriceMapper() {
  }

  /**
   * Model to entity price value.
   *
   * @param model the model
   * @return the price value
   */
  public static Price modelToEntity(PriceView model) {
    Price entity = new Price();

    if (model != null) {
      entity.setValue(MoneyMapper.modelToEntity(model.getValue()));
    }

    return entity;
  }

  /**
   * Entity to model price.
   *
   * @param entity the entity
   * @return the price
   */
  public static PriceView entityToModel(Price entity) {
    PriceView model = new PriceView();

    if (entity != null) {
      model.setId(entity.getId());
      model.setValue(MoneyMapper.entityToModel(entity.getValue()));
    }

    return model;
  }
}
