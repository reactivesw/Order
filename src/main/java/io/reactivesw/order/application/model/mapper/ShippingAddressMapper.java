package io.reactivesw.order.application.model.mapper;

import io.reactivesw.order.application.model.AddressView;
import io.reactivesw.order.domain.model.value.ShippingAddress;

/**
 * shipping address mapper.
 */
public final class ShippingAddressMapper {

  /**
   * private default constructor.
   */
  private ShippingAddressMapper() {
  }

  /**
   * Model to entity shipping address value.
   *
   * @param view the model
   * @return the shipping address value
   */
  public static ShippingAddress toEntity(AddressView view) {
    ShippingAddress address = new ShippingAddress();

    if (view != null) {
//      address.setId(view.getId());

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

  /**
   * Model to entity shipping address value.
   *
   * @param entity the entity
   * @return the shipping address view
   */
  public static AddressView toView(ShippingAddress entity) {
    AddressView address = new AddressView();

    if (entity != null) {
      address.setId(entity.getId());

      address.setFullName(entity.getFullName());

      address.setPhone(entity.getPhone());

      address.setZip(entity.getZip());

      address.setFirstLine(entity.getFirstLine());

      address.setSecondLine(entity.getSecondLine());

      address.setCountry(entity.getCountry());

      address.setState(entity.getState());

      address.setCity(entity.getCity());
    }

    return address;
  }
}
