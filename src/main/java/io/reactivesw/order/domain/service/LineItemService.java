package io.reactivesw.order.domain.service;

import io.reactivesw.order.domain.model.value.LineItem;
import io.reactivesw.order.domain.model.value.MoneyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * line item application.
 */
@Service
public class LineItemService {

  /**
   * logger.
   */
  private static final Logger LOG = LoggerFactory.getLogger(LineItemService.class);


  /**
   * calculate LineItem price.
   *
   * @param item LineItem
   */
  public void calculateItemPrice(LineItem item) {
    LOG.debug("enter. lineItem: {}", item);
    if (item.getPrice() != null) {
      MoneyValue price = item.getPrice();

      int quantity = item.getQuantity() == null ? 0 : item.getQuantity();

      int totalPrice = price.getCentAmount() * quantity;

      MoneyValue total = new MoneyValue();
      total.setCurrencyCode(price.getCurrencyCode());
      total.setCentAmount(totalPrice);
      item.setTotalPrice(total);
    }
    LOG.debug("exit. lineItem: {}", item);
  }
}
