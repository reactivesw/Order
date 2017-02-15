package io.reactivesw.order.application.service;

import io.reactivesw.order.application.model.CartView;
import io.reactivesw.order.application.model.InventoryEntryView;
import io.reactivesw.order.application.model.InventoryRequest;
import io.reactivesw.order.application.model.PaymentView;
import io.reactivesw.order.infrastructure.validator.CartValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Davis on 17/2/6.
 */
@Component
public class OrderRestClient {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(OrderRestClient.class);

  /**
   * RestTemplate.
   */
  @Autowired
  @Qualifier("restTemplate")
  private transient RestTemplate restTemplate;

  /**
   * Gets cart.
   *
   * @param cartId  the cart id
   * @param version the cart version
   * @return the cart
   */
  public CartView getCart(String cartId, Integer version) {
    LOG.debug("enter getCart, cart id is : {}, cart version is : {}", cartId, version);

    String url = "http://localhost:8088/carts/" + cartId;
    CartView result = restTemplate.getForObject(url, CartView.class);

    CartValidator.validateVersion(result, version);

    LOG.debug("end getCart, result is : {}", result);
    return result;
  }


  /**
   * Checkout payment.
   *
   * @param centAmount         the cent amount
   * @param paymentMethodToken the payment method token
   * @return the payment
   */
  public PaymentView checkout(String customerId, Integer centAmount, String paymentMethodToken) {
    LOG.debug("enter checkout, centAmount is : {}, payment method token is : {}", centAmount,
        paymentMethodToken);

    String url = "http://localhost:8088/payments";
    MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
    request.add("customerId", customerId);
    request.add("amount", String.valueOf(centAmount));
    request.add("token", paymentMethodToken);

    PaymentView result = restTemplate.postForObject(url, request, PaymentView.class);
    LOG.debug("end checkout, result is : {}", result);
    return result;
  }

  /**
   * Change inventory entry inventory entry.
   * changeInventoryEntry
   *
   * @return the inventory entry
   */
  public InventoryEntryView changeInventoryEntry(List<InventoryRequest> inventoryRequestList) {
    LOG.debug("enter changeInventoryEntry");

    // TODO: 17/2/6
    String url = "http://localhost:8088/inventory";
    MultiValueMap<String, List> request = new LinkedMultiValueMap<>();
    request.add("requests", inventoryRequestList);
    restTemplate.put(url, request);

    InventoryEntryView result = null;
    LOG.debug("end changeInventoryEntry");

    return result;
  }
}
