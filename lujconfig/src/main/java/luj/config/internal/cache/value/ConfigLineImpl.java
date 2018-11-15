package luj.config.internal.cache.value;

import luj.config.internal.meta.ConfigMeta;

final class ConfigLineImpl implements ConfigValueMapLoaderImpl.ConfigLine {

  ConfigLineImpl(Object configImpl, ConfigMeta configMeta) {
    _configImpl = configImpl;
    _configMeta = configMeta;
  }

  @Override
  public String getId() {
    return _configMeta.getConfigId(_configImpl);
  }

  @Override
  public Object getInstance() {
    return _configImpl;
  }

  private final Object _configImpl;

  private final ConfigMeta _configMeta;
}
