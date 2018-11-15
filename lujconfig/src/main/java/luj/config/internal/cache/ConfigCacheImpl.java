package luj.config.internal.cache;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

final class ConfigCacheImpl implements ConfigCache {

  ConfigCacheImpl(Map<Class<?>, Map<String, Object>> typeMap) {
    _typeMap = checkNotNull(typeMap);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T get(Class<T> configType, String id) {
    Map<String, Object> configMap = _typeMap.getOrDefault(configType, ImmutableMap.of());
    return (T) configMap.get(id);
  }

  private final Map<Class<?>, Map<String, Object>> _typeMap;
}
