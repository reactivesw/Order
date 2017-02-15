package io.reactivesw.order.infrastructure.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivesw.order.domain.model.value.AssetDimensionsView;

import java.io.IOException;

import javax.persistence.AttributeConverter;

/**
 * Created by Davis on 16/11/23.
 */
public class AssetDimensionsJsonConverter implements AttributeConverter<AssetDimensionsView,
    String> {

  private final static ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public String convertToDatabaseColumn(AssetDimensionsView meta) {
    try {
      return objectMapper.writeValueAsString(meta);
    } catch (Exception ex) {
      return null;
      // or throw an error
    }
  }

  @Override
  public AssetDimensionsView convertToEntityAttribute(String dbData) {
    try {
      return objectMapper.readValue(dbData, AssetDimensionsView.class);
    } catch (IOException ex) {
      return null;
    }
  }

}