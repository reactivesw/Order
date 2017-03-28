package io.reactivesw.order.infrastructure.repository;


import io.reactivesw.order.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * order repository.
 */
public interface OrderRepository extends JpaRepository<Order, String> {

  /**
   * find orders by customer id.
   *
   * @param customerId
   * @return
   */
  List<Order> findByCustomerId(String customerId);
}
