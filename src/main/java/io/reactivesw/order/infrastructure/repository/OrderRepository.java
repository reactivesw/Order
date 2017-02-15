package io.reactivesw.order.infrastructure.repository;


import io.reactivesw.order.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Davis on 17/2/4.
 */
public interface OrderRepository extends JpaRepository<Order, String> {
}
