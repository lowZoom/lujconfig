package luj.config.internal.container.type.factory;

import java.util.List;
import luj.config.api.container.ConfigType;

final class ConfigTypeImpl implements ConfigType {

  @Override
  public List<Field> getFields() {
    return _fieldList;
  }

  @Override
  public Class<?> asClass() {
    return _class;
  }

  @Override
  public String toString() {
    return _class.getName();
  }

  Class<?> _class;

  List<Field> _fieldList;
}
