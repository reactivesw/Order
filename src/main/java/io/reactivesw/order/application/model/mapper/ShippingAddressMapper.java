package io.reactivesw.order.application.model.mapper;

import io.reactivesw.order.application.model.AddressView;
import io.reactivesw.order.domain.model.value.ShippingAddressValue;

/**
 * Created by Davis on 17/2/6.
 */
public final class ShippingAddressMapper {
  /**
   * Instantiates a new Shipping address mapper.
   */
  private ShippingAddressMapper() {
  }

  
  /**
   * Model to entity shipping address value.
   *
   * @param view the model
   * @return the shipping address value
   */
  public static ShippingAddressValue modelToEntity(AddressView view) {
    ShippingAddressValue address = new ShippingAddressValue();

    if (view != null) {
      address.setId(view.getId());

      address.setFullName(view.getFullName());

      address.setPhone(view.getPhone());

      address.setZip(view.getZip());

      address.setFirstLine(view.getFirstLine());

      address.setSecondLine(view.getSecondLine());

      address.setCountry(view.getCountry());

      address.setState(view.getState());

      address.setCity(view.getCity());
    }
    
    return address;
  }
}
