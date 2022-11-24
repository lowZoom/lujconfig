package luj.config.api;

import luj.config.internal.session.ConfigSessionFactory;

public enum LujConfig {
  ;

  public static ConfigSession start() {
    return new ConfigSessionFactory().create();
  }
}
