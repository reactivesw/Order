package io.reactivesw.order.application.model.mapper;


import io.reactivesw.order.application.model.TaxRateView;
import io.reactivesw.order.domain.model.value.TaxRateValue;

/**
 * Created by Davis on 17/2/7.
 */
public final class TaxRateMapper {
  /**
   * Instantiates a new Tax rate mapper.
   */
  private TaxRateMapper() {
  }

  /**
   * Model to entity tax rate value.
   *
   * @param model the model
   * @return the tax rate value
   */
  public static TaxRateValue modelToEntity(TaxRateView model) {
    TaxRateValue entity = new TaxRateValue();

    if (model != null) {
      entity.setName(model.getName());
      entity.setAmount(model.getAmount());
      entity.setIncludedInPrice(model.getIncludedInPrice());
      entity.setCountry(model.getCountry());
      entity.setState(model.getState());
      entity.setSubRates(SubRateMapper.modelToEntity(model.getSubRates()));
    }

    return entity;
  }

  /**
   * Entity to model tax rate.
   *
   * @param entity the entity
   * @return the tax rate
   */
  public static TaxRateView entityToModel(TaxRateValue entity) {
    TaxRateView model = new TaxRateView();

    if (entity != null) {
      model.setName(entity.getName());
      model.setAmount(entity.getAmount());
      model.setIncludedInPrice(entity.getIncludedInPrice());
      model.setCountry(entity.getCountry());
      model.setState(entity.getState());
      model.setSubRates(SubRateMapper.entityToModel(entity.getSubRates()));
    }

    return model;
  }
}
