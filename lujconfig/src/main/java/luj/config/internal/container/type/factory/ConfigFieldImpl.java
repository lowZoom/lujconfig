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

  String _name;

  ConfigFieldTypeImpl _type;
}
