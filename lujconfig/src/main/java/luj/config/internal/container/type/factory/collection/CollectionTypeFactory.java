package luj.config.internal.container.type.factory.collection;

import java.util.Collection;
import luj.ava.reflect.type.TypeX;
import luj.config.api.container.ConfigType;
import luj.config.internal.container.type.factory.field.FieldTypeFactory;

public class CollectionTypeFactory {

  public CollectionTypeFactory(TypeX<?> fieldType, ConfigType.Field field) {
    _fieldType = fieldType;
    _field = field;
  }

  public ConfigType.Field.CollectionType create() {
    ConfigFieldCollectTypeImpl type = new ConfigFieldCollectTypeImpl();
    type._class = _fieldType.asClass();
    type._parentField = _field;

    type._elementType = new FieldTypeFactory(_fieldType.getTypeParam(0), _field).create();
    type._dimension = countDimension();

    return type;
  }

  private int countDimension() {
    int count = 0;
    TypeX<?> cursor = _fieldType;

    while (cursor.isAssignableTo(Collection.class)) {
      ++count;
      cursor = cursor.getTypeParam(0);
    }

    return count;
  }

  private final TypeX<?> _fieldType;

  private final ConfigType.Field _field;
}
