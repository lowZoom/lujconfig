package luj.config.internal.meta;

import luj.config.internal.meta.spring.ConfigIdGetter;

final class ConfigMetaImpl implements ConfigMeta {

  ConfigMetaImpl(Class<?> configType, String configName, ConfigIdGetter<Object> idGetter) {
    _configType = configType;

    _configName = configName;
    _idGetter = idGetter;
  }

  @Override
  public Class<?> getConfigType() {
    return _configType;
  }

  @Override
  public String getConfigName() {
    return _configName;
  }

  @Override
  public String getConfigId(Object config) {
    return _idGetter.getId(config);
  }

  private final Class<?> _configType;

  private final String _configName;
  private final ConfigIdGetter<Object> _idGetter;
}
