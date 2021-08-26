package luj.config.api;

import luj.config.api.container.ConfigContainer;

public interface ConfigSession {

  <T> T findConfig(Class<T> configType, String id);

  ConfigContainer createContainer();
}
