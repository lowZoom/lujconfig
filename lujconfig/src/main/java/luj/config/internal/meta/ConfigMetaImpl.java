package luj.config.internal.meta;

final class ConfigMetaImpl implements ConfigMeta {

  ConfigMetaImpl(Class<?> configType, String configName) {
    _configType = configType;
    _configName = configName;
  }

  @Override
  public Class<?> getConfigType() {
    return _configType;
  }

  @Override
  public String getConfigName() {
    return _configName;
  }

  private final Class<?> _configType;

  private final String _configName;
}
