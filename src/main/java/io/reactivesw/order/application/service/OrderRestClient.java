package io.reactivesw.order.application.service;

import io.reactivesw.exception.NotExistException;
import io.reactivesw.order.application.model.CartView;
import io.reactivesw.order.application.model.ProductView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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
  @Value("${cart.service.uri:http://carts/}")
  private transient String cartUri;

  /**
   * product service uri.
   */
  @Value("${product.service.uri:http://products/}")
  private transient String productUri;

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

      //checkout the cart. todo prevent checkout success bug create order failed.
      String url = cartUri + cartId + "/checkout";
      LOG.debug("CartUrl: {}.", url);

      result = restTemplate.getForObject(url, CartView.class);

    } catch (HttpClientErrorException ex) {
      if (ex.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
        LOG.debug("Get Cart failed. cartId: {}.", cartId, ex);
        throw new NotExistException("Cart not exist. cartId: " + cartId);
      } else {
        throw ex;
      }
    }
    LOG.debug("exit. cart: {}.", result);
    return result;
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


    } catch (HttpClientErrorException ex) {
      if (ex.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
        LOG.debug("Get Product failed. productId: {}, variantId: {}.", productId, variantId, ex);
        throw new NotExistException("Product not exist. productId: " + productId);
      } else {
        throw ex;
      }
    }

    LOG.debug("exit: product: {}", product);
    return product;
  }
}
