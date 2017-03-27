package io.reactivesw.order.application.model.mapper;

import io.reactivesw.model.LocalizedString;
import io.reactivesw.order.domain.model.value.LocalizedStringValue;

import java.util.HashMap;
import java.util.Map;

public class LocalizedStringMapper {

  public static LocalizedString toView(LocalizedStringValue value) {
    LocalizedString localizedString = null;
    if (value != null) {
      localizedString = new LocalizedString();
      Map<String, String> localized = new HashMap<>();
      localized.put(value.getLanguage(), value.getText());
      localizedString.setLocalized(localized);
    }
    return localizedString;
  }

  public static LocalizedStringValue toEntity(LocalizedString view) {
    LocalizedStringValue localizedStringValue = null;
    if (view != null) {
      Map<String, String> localized = view.getLocalized();
      if (!localized.isEmpty()) {
        String language = localized.keySet().stream().findFirst().orElse(null);
        String text = localized.get(language);
        localizedStringValue = new LocalizedStringValue();
        localizedStringValue.setLanguage(language);
        localizedStringValue.setText(text);
      }
    }
    return localizedStringValue;
  }
}
