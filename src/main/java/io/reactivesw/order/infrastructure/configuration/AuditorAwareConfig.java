package io.reactivesw.order.infrastructure.configuration;

import io.reactivesw.order.infrastructure.util.ZonedDateTimeAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditorAwareConfig {
  /**
   * Auditor provider zoned date time auditor aware.
   *
   * @return the zoned date time auditor aware
   */
  @Bean
  public ZonedDateTimeAuditorAware auditorProvider() {
    return new ZonedDateTimeAuditorAware();
  }
}