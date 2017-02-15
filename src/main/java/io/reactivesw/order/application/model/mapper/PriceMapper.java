package io.reactivesw.order.application.model.mapper;

import io.reactivesw.model.Reference;
import io.reactivesw.order.application.model.PriceView;
import io.reactivesw.order.domain.model.value.PriceValue;
import io.reactivesw.order.infrastructure.enums.ReferenceTypes;

/**
 * Created by Davis on 17/2/7.
 */
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
  public static PriceValue modelToEntity(PriceView model) {
    PriceValue entity = new PriceValue();

    if (model != null) {
      entity.setValue(MoneyMapper.modelToEntity(model.getValue()));
      entity.setCountry(model.getCountry());
      if (model.getCustomerGroup() != null) {
        entity.setCustomerGroup(model.getCustomerGroup().getId());
      }
      if (model.getChannel() != null) {
        entity.setChannel(model.getChannel().getId());
      }
      entity.setValidFrom(model.getValidFrom());
      entity.setValidUntil(model.getValidUntil());
    }

    return entity;
  }

  /**
   * Entity to model price.
   *
   * @param entity the entity
   * @return the price
   */
  public static PriceView entityToModel(PriceValue entity) {
    PriceView model = new PriceView();

    if (entity != null) {
      model.setId(entity.getId());
      model.setValue(MoneyMapper.entityToModel(entity.getValue()));
      model.setCountry(entity.getCountry());
      model.setCustomerGroup(new Reference(
          ReferenceTypes.CUSTOMERGROUP.toString(), entity.getCustomerGroup()));
      model.setChannel(new Reference(
          ReferenceTypes.CHANNEL.toString(), entity.getChannel()
      ));
      model.setValidFrom(entity.getValidFrom());
      model.setValidUntil(entity.getValidUntil());
    }

    return model;
  }
}
