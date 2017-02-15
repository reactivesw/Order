package io.reactivesw.order.application.model.mapper;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.reactivesw.model.Reference;
import io.reactivesw.order.application.model.LineItemView;
import io.reactivesw.order.domain.model.value.LineItemValue;
import io.reactivesw.order.infrastructure.enums.ReferenceTypes;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Davis on 17/2/7.
 */
public final class LineItemMapper {
  /**
   * Instantiates a new Line item mapper.
   */
  private LineItemMapper() {
  }

  /**
   * Model to entity list.
   *
   * @param models the models
   * @return the list
   */
  public static Set<LineItemValue> modelToEntity(List<LineItemView> models) {
    Set<LineItemValue> entities = Sets.newHashSet();

    if (models != null) {
      entities = models.parallelStream().map(
          model -> modelToEntity(model)
      ).collect(Collectors.toSet());
    }

    return entities;
  }

  /**
   * Model to entity line item value.
   *
   * @param model the model
   * @return the line item value
   */
  public static LineItemValue modelToEntity(LineItemView model) {
    LineItemValue entity = new LineItemValue();

    entity.setProductId(model.getProductId());
    entity.setName(LocalizedStringMapper.modelToEntityDefaultNull(model.getName()));
    entity.setProductSlug(model.getSlug());
    entity.setVariant(null);
    entity.setPrice(PriceMapper.modelToEntity(model.getPrice()));
    entity.setTaxedPrice(TaxedItemPriceMapper.modelToEntity(model.getTaxedPrice()));
    entity.setTotalPrice(MoneyMapper.modelToEntity(model.getTotalPrice()));
    entity.setQuantity(model.getQuantity());
    entity.setState(null);
    entity.setTaxRate(TaxRateMapper.modelToEntity(model.getTaxRate()));

    String supplyChannel = model.getSupplyChannel() == null ? null : model.getSupplyChannel()
        .getId();
    entity.setSupplyChannel(supplyChannel);

    String distributionChannel = model.getDistributionChannel() == null ? null : model
        .getDistributionChannel().getId();
    entity.setDistributionChannel(distributionChannel);

    entity.setDiscountedPriceForQuantity(null);
    entity.setPriceMode(null);

    return entity;
  }

  /**
   * Entity to model list.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<LineItemView> entityToModel(Set<LineItemValue> entities) {
    List<LineItemView> models = Lists.newArrayList();

    if (entities != null) {
      models = entities.parallelStream().map(
          entity -> {
            return entityToModel(entity);
          }
      ).collect(Collectors.toList());
    }

    return models;
  }

  public static LineItemView entityToModel(LineItemValue entity) {
    LineItemView model = new LineItemView();

    if (entity != null) {
      model.setId(entity.getId());
      model.setProductId(entity.getProductId());
      model.setName(LocalizedStringMapper.entityToModelDefaultNew(entity.getName()));
      model.setSlug(entity.getProductSlug());
      model.setProductVariant(null);
      model.setPrice(PriceMapper.entityToModel(entity.getPrice()));
      model.setTaxedPrice(TaxedItemPriceMapper.entityToModel(entity.getTaxedPrice()));
      model.setTotalPrice(MoneyMapper.entityToModel(entity.getTotalPrice()));
      model.setQuantity(entity.getQuantity());
      model.setTaxRate(TaxRateMapper.entityToModel(entity.getTaxRate()));
      model.setSupplyChannel(new Reference(
          ReferenceTypes.CHANNEL.toString(), entity.getSupplyChannel()));
      model.setDistributionChannel(new Reference(
          ReferenceTypes.CHANNEL.toString(), entity.getDistributionChannel()
      ));
    }

    return model;
  }
}
