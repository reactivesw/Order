package io.reactivesw.order.application.service;

import io.reactivesw.order.application.model.CartView;
import io.reactivesw.order.application.model.ProductView;
import io.reactivesw.order.infrastructure.validator.CartValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderRestClient {

  /**
   * log.
   */
  private static final Logger LOG = LoggerFactory.getLogger(OrderRestClient.class);

  @Value("${cart.service.uri}")
  private transient String cartUri;

  @Value("${product.service.uri}")
  private transient String productUri;

  /**
   * RestTemplate.
   */
  private transient RestTemplate restTemplate = new RestTemplate();

  /**
   * Gets cart.
   *
   * @param cartId  the cart id
   * @param version the cart version
   * @return the cart
   */
  public CartView getCart(String cartId, Integer version) {
    LOG.debug("enter getCart, cart id is : {}, cart version is : {}", cartId, version);

    String url = cartUri + cartId;
    CartView result = restTemplate.getForObject(url, CartView.class);

    CartValidator.validateVersion(result, version);

    LOG.debug("end getCart, result is : {}", result);
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

    String url = productUri + "CartProducts/" + productId + "?variantId=" +
        variantId;
    ProductView product = restTemplate.getForObject(url, ProductView.class);

    LOG.debug("exit: product: {}", product);
    return product;
  }
}
