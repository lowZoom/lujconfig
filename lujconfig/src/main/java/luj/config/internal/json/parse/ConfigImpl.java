package luj.config.internal.json.parse;

import luj.config.internal.meta.ConfigMeta;

final class ConfigImpl implements LineJsonParserImpl.Config {

  ConfigImpl(ConfigMeta meta) {
    _meta = meta;
  }

  @Override
  public Class<?> getConfigInterface() {
    return _meta.getConfigType();
  }

  @Override
  public Object createInstance() {
    return _meta.createConfigInstance();
  }

  private final ConfigMeta _meta;
}
