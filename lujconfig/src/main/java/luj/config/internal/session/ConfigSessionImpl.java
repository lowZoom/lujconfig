package luj.config.internal.session;

import luj.config.api.ConfigSession;
import luj.config.internal.cache.ConfigCache;

final class ConfigSessionImpl implements ConfigSession {

  ConfigSessionImpl(ConfigCache configCache) {
    _configCache = configCache;
  }

  @Override
  public <T> T findConfig(Class<T> configType, String id) {
    return _configCache.get(configType, id);
  }

  private final ConfigCache _configCache;
}
