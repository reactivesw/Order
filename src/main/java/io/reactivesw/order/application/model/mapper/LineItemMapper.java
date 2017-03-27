package io.reactivesw.order.application.model.mapper;

import com.google.common.collect.Lists;
import io.reactivesw.model.Money;
import io.reactivesw.order.application.model.LineItemView;
import io.reactivesw.order.domain.model.value.LineItem;
import io.reactivesw.order.domain.model.value.MoneyValue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
  public static List<LineItem> modelToEntity(List<LineItemView> models) {
    List<LineItem> entities = new ArrayList<>();

    if (models != null) {
      entities = models.stream().map(
          model -> modelToEntity(model)
      ).collect(Collectors.toList());
    }

    return entities;
  }

  /**
   * Model to entity line item value.
   *
   * @param model the model
   * @return the line item value
   */
  public static LineItem modelToEntity(LineItemView model) {
    LineItem entity = new LineItem();

    entity.setProductId(model.getProductId());
    entity.setName(LocalizedStringMapper.modelToEntityDefaultNull(model.getName()));
    if (entity.getPrice() != null) {
      MoneyValue money = new MoneyValue(model.getPrice().getCurrencyCode(), model.getPrice()
          .getCentAmount());
      entity.setPrice(money);
    }
    entity.setTotalPrice(MoneyMapper.modelToEntity(model.getTotalPrice()));
    entity.setQuantity(model.getQuantity());


    return entity;
  }

  /**
   * Entity to model list.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<LineItemView> entityToModel(List<LineItem> entities) {
    List<LineItemView> models = Lists.newArrayList();

    if (entities != null) {
      models = entities.stream().map(
          entity -> entityToModel(entity)
      ).collect(Collectors.toList());
    }

    return models;
  }

  public static LineItemView entityToModel(LineItem entity) {
    LineItemView model = new LineItemView();

    if (entity != null) {
      model.setId(entity.getId());
      model.setProductId(entity.getProductId());
      model.setName(LocalizedStringMapper.entityToModelDefaultNew(entity.getName()));
      model.setVariantId(entity.getVariantId());

      if (entity.getPrice() != null) {
        Money moneyView = new Money(entity.getPrice().getCurrencyCode(), entity.getPrice()
            .getCentAmount());
        model.setPrice(moneyView);
      }

      model.setTotalPrice(MoneyMapper.entityToModel(entity.getTotalPrice()));
      model.setQuantity(entity.getQuantity());
    }
    return model;
  }
}
