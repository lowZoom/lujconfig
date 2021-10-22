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

    TypeX<?> elemType = _fieldType.getTypeParam(0);
    type._elementType = new FieldTypeFactory(elemType, _field).create();
    type._dimension = countDimension(elemType);

    return type;
  }

  private int countDimension(TypeX<?> elemType) {
    int count = 1;
    TypeX<?> cursor = elemType;

    while (cursor.isAssignableTo(Collection.class)) {
      ++count;
      cursor = cursor.getTypeParam(0);
    }

    return count;
  }

  private final TypeX<?> _fieldType;

  private final ConfigType.Field _field;
}
