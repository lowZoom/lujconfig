package luj.config.internal.session;

import luj.ava.spring.Internal;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(
    basePackages = "luj.config",
    includeFilters = @ComponentScan.Filter(Internal.class)
)
final class InjectConfig {
  // NOOP
}
