package luj.config.internal.cache.link;

import static com.google.common.collect.ImmutableMap.toImmutableMap;

import java.util.Map;
import luj.config.internal.cache.value.ConfigValueMapLoader;

enum ConfigInstanceLinkerFactoryImpl implements ConfigInstanceLinker.Factory {
  SINGLETON;

  @Override
  public ConfigInstanceLinker create(
      Map<Class<?>, Map<String, ConfigValueMapLoader.Value>> valueMap) {
    return new ConfigInstanceLinkerImpl(valueMap.entrySet().stream()
        .collect(toImmutableMap(Map.Entry::getKey, e -> toValueMap(e.getValue()))));
  }

  private Map<String, ConfigInstanceLinkerImpl.ConfigValue> toValueMap(
      Map<String, ConfigValueMapLoader.Value> rawMap) {
    return rawMap.entrySet().stream().collect(
        toImmutableMap(Map.Entry::getKey, e -> new ConfigValueImpl(e.getValue())));
  }
}
