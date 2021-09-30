package luj.config.internal.container.type.factory;

import java.util.Collection;
import luj.config.api.container.ConfigType;

final class ConfigFieldTypeImpl implements ConfigType.Field.Type {

  @Override
  public boolean isReference() {
    return _class.isInterface() && !Collection.class.isAssignableFrom(_class);
  }

  @Override
  public Class<?> asClass() {
    return _class;
  }

  Class<?> _class;
}
