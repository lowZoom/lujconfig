package luj.config.internal.session;

import luj.ava.spring.Internal;
import luj.config.api.ConfigSession;
import luj.config.internal.cache.ConfigCache;
import luj.config.internal.cache.ConfigCacheLoader;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ConfigSessionFactoryImpl implements ConfigSessionFactory {

  @Override
  public ConfigSession create() {
    ConfigCache cache = _configCacheLoaderFactory.create().load();
    return new ConfigSessionImpl(cache);
  }

  @Autowired
  private ConfigCacheLoader.Factory _configCacheLoaderFactory;
}
