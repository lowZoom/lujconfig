package luj.config.internal.container.type.factory.collection;

import luj.config.api.container.ConfigType;

final class ConfigFieldCollectTypeImpl implements ConfigType.Field.CollectionType {

  @Override
  public Class<?> asClass() {
    return _class;
  }

  @Override
  public ConfigType.Field getParentField() {
    return _parentField;
  }

  @Override
  public ConfigType.Field.Type getElementType() {
    return _elementType;
  }

  @Override
  public int getDimension() {
    return _dimension;
  }

  Class<?> _class;
  ConfigType.Field _parentField;

  ConfigType.Field.Type _elementType;
  int _dimension;
}
