package luj.config.internal.json.parse.field;

import java.util.List;

enum FieldValueHandlerFactoryImpl implements FieldValueHandlerFactory {
  SINGLETON;

  @Override
  public FieldValueHandler create(Class<?> fieldType) {
    if (fieldType == String.class) {
      return FieldString.SINGLETON;
    }
    if (fieldType == List.class) {
      return FieldList.SINGLETON;
    }
    return null;
  }
}
