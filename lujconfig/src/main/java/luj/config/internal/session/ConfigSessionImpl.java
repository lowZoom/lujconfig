package luj.config.internal.session;

import luj.config.api.ConfigSession;
import luj.config.api.container.ConfigContainer;
import luj.config.internal.cache.ConfigCache;
import luj.config.internal.container.ContainerFactory;

final class ConfigSessionImpl implements ConfigSession {

  ConfigSessionImpl(ConfigCache configCache) {
    _configCache = configCache;
  }

  @Override
  public <T> T findConfig(Class<T> configType, String id) {
    return _configCache.get(configType, id);
  }

  @Override
  public ConfigContainer createContainer() {
    return ContainerFactory.GET.create();
  }

  private final ConfigCache _configCache;
}
