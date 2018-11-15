package luj.config.internal.cache.link;

import static com.google.common.collect.ImmutableMap.toImmutableMap;

import java.util.List;
import java.util.Map;

final class ConfigInstanceLinkerImpl implements ConfigInstanceLinker {

  ConfigInstanceLinkerImpl(Map<Class<?>, Map<String, ConfigValue>> typeMap) {
    _typeMap = typeMap;
  }

  @Override
  public Map<Class<?>, Map<String, Object>> link() {
    return _typeMap.entrySet().stream().collect(
        toImmutableMap(Map.Entry::getKey, e -> linkImpl(e.getValue())));
  }

  private Map<String, Object> linkImpl(Map<String, ConfigValue> valueMap) {
    valueMap.values().stream()
        .flatMap(v -> v.getLinkableList().stream())
        .forEach(LinkableField::link);

    return valueMap.entrySet().stream().collect(
        toImmutableMap(Map.Entry::getKey, e -> e.getValue().getConfigInstance()));
  }

  interface ConfigValue {

    List<LinkableField> getLinkableList();

    Object getConfigInstance();
  }

  interface LinkableField {

    void link();
  }

  private final Map<Class<?>, Map<String, ConfigValue>> _typeMap;
}
