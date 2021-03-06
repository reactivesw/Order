package io.reactivesw.order.application.service;

import io.reactivesw.order.application.model.AddressView;
import io.reactivesw.order.application.model.CartView;
import io.reactivesw.order.application.model.InventoryRequest;
import io.reactivesw.order.application.model.PayRequest;
import io.reactivesw.order.application.model.PaymentView;
import io.reactivesw.order.application.model.ProductView;
import io.reactivesw.order.infrastructure.exception.ReserveInventoryException;
import io.reactivesw.order.infrastructure.exception.CheckoutCartException;
import io.reactivesw.order.infrastructure.exception.GetAddressException;
import io.reactivesw.order.infrastructure.exception.GetProductException;
import io.reactivesw.order.infrastructure.exception.PayOrderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * order rest client.
 */
@Component
public class OrderRestClient {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(OrderRestClient.class);

  /**
   * cart service uri
   */
  @Value("${cart.service.uri:http://cart/}")
  private transient String cartUri;

  /**
   * cart service uri
   */
  @Value("${customer.service.uri:http://customer-info/}")
  private transient String customerUri;

  /**
   * cart service uri
   */
  @Value("${inventory.service.uri:http://inventory/}")
  private transient String inventoryUri;

  /**
   * product service uri.
   */
  @Value("${product.service.uri:http://product/}")
  private transient String productUri;

  /**
   * payment service uri.
   */
  @Value("${payment.service.uri:http://payment/}")
  private transient String paymentUri;

  /**
   * RestTemplate.
   */
  private transient RestTemplate restTemplate = new RestTemplate();

  /**
   * Gets cart.
   *
   * @param cartId the cart id
   * @return the cart
   */
  public CartView getCart(String cartId) {
    LOG.debug("enter. cartId: {}.", cartId);

    CartView result = null;
    try {

      //checkout the cart.
      String url = cartUri + cartId + "/checkout";
      LOG.debug("CartUrl: {}.", url);

      result = restTemplate.getForObject(url, CartView.class);

    } catch (HttpClientErrorException | HttpServerErrorException ex) {
      LOG.debug("Checkout Cart failed. cartId: {}.", cartId, ex);
      throw new CheckoutCartException("Checkout cart failed. cartId: " + cartId);
    }
    LOG.debug("exit. cart: {}.", result);
    return result;
  }

  /**
   * Gets address.
   *
   * @param addressId the cart id
   * @return the cart
   */
  public AddressView getAddress(String addressId) {
    LOG.debug("enter. addressId: {}.", addressId);

    AddressView result = null;
    try {

      //checkout the cart. todo prevent checkout success bug create order failed.
      String url = customerUri + "addresses/" + addressId;
      LOG.debug("AddressUrl: {}.", url);

      result = restTemplate.getForObject(url, AddressView.class);

    } catch (HttpClientErrorException | HttpServerErrorException ex) {
      LOG.debug("Get Address failed. addressId: {}.", addressId, ex);
      throw new GetAddressException("Get Address failed. addressId: " + addressId);
    }
    LOG.debug("exit. address: {}.", result);
    return result;
  }

  /**
   * change product quantity.
   *
   * @param requestList request list
   */
  public void changeProductInventory(List<InventoryRequest> requestList) {
    LOG.debug("enter. requestList: {}.", requestList);

    try {

      //checkout the cart. todo prevent checkout success bug create order failed.
      String url = inventoryUri;
      LOG.debug("InventoryUrl: {}.", url);

      restTemplate.put(url, requestList);

    } catch (HttpClientErrorException | HttpServerErrorException ex) {
      LOG.debug("change inventory failed: {}", ex.getMessage());
      throw new ReserveInventoryException("change inventory failed.");
    }
    LOG.debug("exit.");
  }

  /**
   * Gets product data from product service.
   *
   * @param productId the address id
   * @return the Product
   */
  public ProductView getProduct(String productId, Integer variantId) {
    LOG.debug("enter: productId: {}", productId);
    ProductView product = null;

    try {

      String url = productUri + "CartProducts/" + productId + "?variantId=" +
          variantId;
      product = restTemplate.getForObject(url, ProductView.class);

    } catch (HttpClientErrorException | HttpServerErrorException ex) {
      LOG.debug("Get Product failed. productId: {}, variantId: {}.", productId, variantId, ex);
      throw new GetProductException("Get Product failed. productId: " + productId);
    }

    LOG.debug("exit: product: {}", product);
    return product;
  }

  /**
   * Gets product data from product service.
   *
   * @param request the address id
   * @return the Product
   */
  public PaymentView pay(PayRequest request) {
    LOG.debug("enter: request: {}.", request);
    PaymentView payment = null;

    try {

      String url = paymentUri;
      payment = restTemplate.postForObject(url, request, PaymentView.class);

    } catch (HttpClientErrorException | HttpServerErrorException ex) {
      LOG.debug("pay order failed: {}", ex.getMessage());
      throw new PayOrderException("Pay order failed.");
    }

    LOG.debug("exit: payment: {}", payment);
    return payment;
  }

}
