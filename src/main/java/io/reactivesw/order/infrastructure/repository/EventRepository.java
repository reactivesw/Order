package io.reactivesw.order.infrastructure.repository;

import io.reactivesw.order.domain.model.EventMessage;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Customer repository.
 */
@Repository
public interface EventRepository extends CrudRepository<EventMessage, String>,
    JpaSpecificationExecutor<EventMessage> {

}
