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
   * @param model the model
   * @return the billing address value
   */
  public static BillingAddressValue modelToEntity(AddressView model) {
    BillingAddressValue entity = new BillingAddressValue();

    if (model != null) {
      entity.setTitle(model.getTitle());
      entity.setSalutation(model.getSalutation());
      entity.setFirstName(model.getFirstName());
      entity.setLastName(model.getLastName());
      entity.setStreetName(model.getStreetName());
      entity.setStreetNumber(model.getStreetNumber());
      entity.setAdditionalStreetInfo(model.getAdditionalStreetInfo());
      entity.setPostalCode(model.getPostalCode());
      entity.setCity(model.getCity());
      entity.setRegion(model.getRegion());
      entity.setState(model.getState());
      entity.setCountry(model.getCountry());
      entity.setCompany(model.getCompany());
      entity.setDepartment(model.getDepartment());
      entity.setBuilding(model.getBuilding());
      entity.setApartment(model.getApartment());
      entity.setPhone(model.getPhone());
      entity.setMobile(model.getMobile());
      entity.setEmail(model.getEmail());
      entity.setFax(model.getFax());
      entity.setAdditionalAddressInfo(model.getAdditionalAddressInfo());
      entity.setExternalId(model.getExternalId());
    }

    return entity;
  }
}
