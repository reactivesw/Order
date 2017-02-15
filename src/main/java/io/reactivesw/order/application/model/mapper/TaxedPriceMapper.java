package io.reactivesw.order.application.model.mapper;

import io.reactivesw.order.application.model.TaxedPriceView;
import io.reactivesw.order.domain.model.value.TaxedPriceValue;

/**
 * Created by Davis on 17/2/6.
 */
public final class TaxedPriceMapper {
  /**
   * Instantiates a new Taxed price mapper.
   */
  public TaxedPriceMapper() {
  }

  /**
   * Model to entity taxed price value.
   *
   * @param model the model
   * @return the taxed price value
   */
  public static TaxedPriceValue modelToEntity(TaxedPriceView model) {
    TaxedPriceValue entity = new TaxedPriceValue();

    if (model != null) {
      entity.setTotalNet(MoneyMapper.modelToEntity(model.getTotalNet()));
      entity.setTotalGross(MoneyMapper.modelToEntity(model.getTotalGross()));
      // TODO: 17/2/6
      entity.setTaxPortions(null);
    }

    return entity;
  }

  /**
   * Entity to model taxed price.
   *
   * @param entity the taxed price
   * @return the taxed price
   */
  public static TaxedPriceView entityToModel(TaxedPriceValue entity) {
    TaxedPriceView model = new TaxedPriceView();

    if (entity != null) {
      model.setTotalNet(MoneyMapper.entityToModel(entity.getTotalNet()));
      model.setTotalGross(MoneyMapper.entityToModel(entity.getTotalGross()));
    }

    return model;
  }
}
