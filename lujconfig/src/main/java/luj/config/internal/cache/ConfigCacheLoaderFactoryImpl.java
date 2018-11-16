package luj.config.internal.cache;

import luj.ava.spring.Internal;
import luj.config.internal.cache.value.ConfigValueMapLoader;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ConfigCacheLoaderFactoryImpl implements ConfigCacheLoader.Factory {

  @Override
  public ConfigCacheLoader create() {
    ConfigValueMapLoader valueMapLoader = _valueMapLoaderFactory.create();
    return new ConfigCacheLoaderImpl(valueMapLoader);
  }

  @Autowired
  private ConfigValueMapLoader.Factory _valueMapLoaderFactory;
}
