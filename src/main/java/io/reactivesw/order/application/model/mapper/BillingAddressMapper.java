package io.reactivesw.order.application.model.mapper;

import io.reactivesw.order.application.model.AddressView;
import io.reactivesw.order.domain.model.value.BillingAddressValue;

/**
 * Created by Davis on 17/2/6.
 */
public final class BillingAddressMapper {
  /**
   * Instantiates a new Billing address mapper.
   */
  private BillingAddressMapper() {
  }

  /**
   * Model to entity billing address value.
   *
   * @param view the model
   * @return the billing address value
   */
  public static BillingAddressValue modelToEntity(AddressView view) {
    BillingAddressValue address = new BillingAddressValue();

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
