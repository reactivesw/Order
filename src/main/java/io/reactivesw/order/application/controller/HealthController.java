package io.reactivesw.order.application.controller;

import static io.reactivesw.order.infrastructure.Router.ORDER_HEALTH_CHECK;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

  /**
   * service name.
   */
  @Value("${spring.application.name}")
  private String serviceName;

  /**
   * this api is used for health check.
   *
   * @return service name.
   */
  @GetMapping(ORDER_HEALTH_CHECK)
  public String health() {
    return serviceName + ", system time: " + System.currentTimeMillis();
  }
}
