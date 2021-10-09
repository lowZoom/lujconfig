package luj.config.internal.container.type.factory.field;

import java.util.Collection;
import luj.ava.reflect.type.TypeX;
import luj.config.api.container.ConfigType;

public class FieldTypeFactory {

  public FieldTypeFactory(TypeX<?> type, ConfigType.Field field) {
    _type = type;
    _field = field;
  }

  public ConfigType.Field.Type create() {
    ConfigFieldTypeImpl type = new ConfigFieldTypeImpl();
    type._type = _type;
    type._class = _type.asClass();

    type._isCollection = _type.isAssignableTo(Collection.class);
    type._parentField = _field;

    return type;
  }

  private final TypeX<?> _type;

  private final ConfigType.Field _field;
}
