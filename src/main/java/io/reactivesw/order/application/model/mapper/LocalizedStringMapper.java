package io.reactivesw.order.application.model.mapper;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.order.domain.model.value.LocalizedStringValue;

public class LocalizedStringMapper {

  public static LocalizedString entityToModelDefaultNew(LocalizedStringValue value){
    return null;
  }

  public static LocalizedStringValue modelToEntityDefaultNull(LocalizedString view){
    return null;
  }
}
