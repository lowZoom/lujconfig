package luj.config.internal.container.type.factory.field;

import java.util.Optional;
import luj.ava.reflect.type.TypeX;
import luj.config.api.container.ConfigType;
import luj.config.internal.container.type.factory.collection.CollectionTypeFactory;

final class ConfigFieldTypeImpl implements ConfigType.Field.Type {

  @Override
  public boolean isReference() {
    return _class.isInterface() && !_isCollection;
  }

  @Override
  public Optional<ConfigType.Field.CollectionType> asCollectionType() {
    if (!_isCollection) {
      return Optional.empty();
    }

    CollectionTypeFactory factory = new CollectionTypeFactory(_type, _parentField);
    return Optional.of(factory.create());
  }

  @Override
  public Class<?> asClass() {
    return _class;
  }

  @Override
  public ConfigType.Field getParentField() {
    return _parentField;
  }

  @Override
  public String toString() {
    return _type.toString().replaceAll("\\bjava\\.(\\w+\\.)+", "");
  }

  TypeX<?> _type;

  Class<?> _class;
  boolean _isCollection;

  ConfigType.Field _parentField;
}
