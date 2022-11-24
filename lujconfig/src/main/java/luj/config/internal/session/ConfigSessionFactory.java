package luj.config.internal.session;

import luj.config.api.ConfigSession;

public class ConfigSessionFactory {

  public ConfigSession create() {
    return new ConfigSessionImpl();
  }
}
