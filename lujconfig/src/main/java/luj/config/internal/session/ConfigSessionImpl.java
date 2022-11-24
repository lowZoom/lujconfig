package luj.config.internal.session;

import luj.config.api.ConfigSession;
import luj.config.api.container.ConfigContainer;
import luj.config.internal.container.ContainerFactory;

final class ConfigSessionImpl implements ConfigSession {

  @Override
  public ConfigContainer createContainer() {
    return ContainerFactory.GET.create();
  }
}
