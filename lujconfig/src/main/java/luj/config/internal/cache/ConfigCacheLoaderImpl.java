package luj.config.internal.cache;

import java.util.Map;
import luj.config.internal.cache.link.ConfigInstanceLinker;
import luj.config.internal.cache.value.ConfigValueMapLoader;

final class ConfigCacheLoaderImpl implements ConfigCacheLoader {

  ConfigCacheLoaderImpl(ConfigValueMapLoader valueMapLoader) {
    _valueMapLoader = valueMapLoader;
  }

  @Override
  public ConfigCache load() {
    Map<Class<?>, Map<String, ConfigValueMapLoader.Value>> valueMap = _valueMapLoader.load();

    ConfigInstanceLinker.Factory linkerFactory = ConfigInstanceLinker.Factory.getInstance();
    ConfigInstanceLinker linker = linkerFactory.create(valueMap);

    Map<Class<?>, Map<String, Object>> cacheMap = linker.link();
    return new ConfigCacheImpl(cacheMap);
  }

  private final ConfigValueMapLoader _valueMapLoader;
}
