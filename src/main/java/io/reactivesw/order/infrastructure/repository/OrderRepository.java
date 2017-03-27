package io.reactivesw.order.infrastructure.repository;


import io.reactivesw.order.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {

  List<Order> findByCustomerId(String customerId);
}
