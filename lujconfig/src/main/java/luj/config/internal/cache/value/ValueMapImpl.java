package luj.config.internal.cache.value;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

final class ValueMapImpl implements ConfigValueMapLoader.ValueMap {

  ValueMapImpl(Map<Class<?>, Map<String, ConfigValueMapLoader.Value>> typeMap) {
    _typeMap = typeMap;
  }

  @Override
  public ConfigValueMapLoader.Value get(Class<?> configType, String id) {
    return _typeMap.getOrDefault(configType, ImmutableMap.of()).get(id);
  }

  private final Map<Class<?>, Map<String, ConfigValueMapLoader.Value>> _typeMap;
}
