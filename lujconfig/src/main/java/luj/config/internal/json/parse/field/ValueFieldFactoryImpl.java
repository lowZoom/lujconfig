package luj.config.internal.json.parse.field;

import java.util.List;

enum ValueFieldFactoryImpl implements ValueFieldFactory {
  SINGLETON;

  @Override
  public FieldValueSetter create(Class<?> fieldType) {
    if (fieldType == String.class) {
      return FieldString.SINGLETON;
    }
    if (fieldType == List.class) {
      return FieldList.SINGLETON;
    }
    return null;
  }
}
