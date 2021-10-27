package luj.config.internal.container.type.factory;

import luj.config.api.container.ConfigType;

final class ConfigFieldImpl implements ConfigType.Field {

  @Override
  public String getName() {
    return _name;
  }

  @Override
  public Type getType() {
    return _type;
  }

  @Override
  public String toString() {
    return _name + ":" + _type;
  }

  String _name;

  Type _type;
}
