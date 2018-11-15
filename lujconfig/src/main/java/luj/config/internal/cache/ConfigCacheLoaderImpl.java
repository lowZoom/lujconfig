package luj.config.internal.cache;

import java.util.Map;
import luj.config.internal.cache.value.ConfigValueMapLoader;

final class ConfigCacheLoaderImpl implements ConfigCacheLoader {

  ConfigCacheLoaderImpl(ConfigValueMapLoader valueMapLoader) {
    _valueMapLoader = valueMapLoader;
  }

  @Override
  public ConfigCache load() {
    ConfigValueMapLoader.ValueMap valueMap = _valueMapLoader.load();

    //TODO: 展开其他配置对象关联

    Map<Class<?>, Map<String, Object>> cacheMap = null;

    return new ConfigCacheImpl(cacheMap);
  }

  private final ConfigValueMapLoader _valueMapLoader;
}
