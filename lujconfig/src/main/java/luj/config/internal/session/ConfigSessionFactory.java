package luj.config.internal.session;

import luj.ava.spring.InternalContext;
import luj.config.api.ConfigSession;

public interface ConfigSessionFactory {

  static ConfigSessionFactory getInstance() {
    return InternalContext.Factory.create(InjectConfig.class)
        .getRootBean(ConfigSessionFactory.class);
  }

  ConfigSession create();
}
