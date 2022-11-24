package luj.config.api;

import luj.config.api.container.ConfigContainer;

public interface ConfigSession {

  ConfigContainer createContainer();
}
