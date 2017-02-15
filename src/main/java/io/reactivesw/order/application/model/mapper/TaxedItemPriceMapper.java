package io.reactivesw.order.application.model.mapper;


import io.reactivesw.order.application.model.TaxedItemPriceView;
import io.reactivesw.order.domain.model.value.TaxedItemPriceValue;

/**
 * Created by Davis on 17/2/7.
 */
public final class TaxedItemPriceMapper {
  /**
   * Instantiates a new Taxed item price mapper.
   */
  private TaxedItemPriceMapper() {
  }

  /**
   * Model to entity taxed item price value.
   *
   * @param model the model
   * @return the taxed item price value
   */
  public static TaxedItemPriceValue modelToEntity(TaxedItemPriceView model) {
    TaxedItemPriceValue entity = new TaxedItemPriceValue();

    if (model != null) {
      entity.setTotalNet(MoneyMapper.modelToEntity(model.getTotalNet()));
      entity.setTotalGross(MoneyMapper.modelToEntity(model.getTotalGross()));
    }

    return entity;
  }

  /**
   * Entity to model taxed item price.
   *
   * @param entity the entity
   * @return the taxed item price
   */
  public static TaxedItemPriceView entityToModel(TaxedItemPriceValue entity) {
    TaxedItemPriceView model = new TaxedItemPriceView();

    if (entity != null) {
      model.setTotalNet(MoneyMapper.entityToModel(entity.getTotalNet()));
      model.setTotalGross(MoneyMapper.entityToModel(entity.getTotalGross()));
    }

    return model;
  }
}
