package io.reactivesw.order.application.model.mapper;

import io.reactivesw.order.application.model.LineItemView;
import io.reactivesw.order.domain.model.value.LineItem;

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
  public static List<LineItem> toEntity(List<LineItemView> models) {
    List<LineItem> entities = new ArrayList<>();

    if (models != null) {
      entities = models.stream().map(
          model -> toEntity(model)
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
  public static LineItem toEntity(LineItemView model) {
    LineItem entity = new LineItem();

    entity.setProductId(model.getProductId());
    entity.setName(LocalizedStringMapper.toEntity(model.getName()));
    entity.setVariantId(model.getVariantId());
    entity.setSku(model.getSku());
    entity.setPrice(MoneyMapper.toEntity(model.getPrice()));
    entity.setImages(ImageMapper.toEntities(model.getImages()));
    entity.setTotalPrice(MoneyMapper.toEntity(model.getTotalPrice()));
    entity.setQuantity(model.getQuantity());


    return entity;
  }

  /**
   * Entity to model list.
   *
   * @param entities the entities
   * @return the list
   */
  public static List<LineItemView> toViews(List<LineItem> entities) {
    List<LineItemView> models = new ArrayList<>();

    if (entities != null) {
      entities.stream().forEach(
          entity -> models.add(toView(entity))
      );
    }

    return models;
  }

  public static LineItemView toView(LineItem entity) {
    LineItemView model = new LineItemView();

    if (entity != null) {
      model.setId(entity.getId());
      model.setProductId(entity.getProductId());
      model.setName(LocalizedStringMapper.toView(entity.getName()));
      model.setVariantId(entity.getVariantId());
      model.setSku(entity.getSku());
      model.setPrice(MoneyMapper.toView(entity.getPrice()));
      model.setImages(ImageMapper.toViews(entity.getImages()));
      model.setTotalPrice(MoneyMapper.toView(entity.getTotalPrice()));
      model.setQuantity(entity.getQuantity());
    }
    return model;
  }
}
