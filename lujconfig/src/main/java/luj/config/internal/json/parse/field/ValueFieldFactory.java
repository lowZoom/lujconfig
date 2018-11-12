package luj.config.internal.json.parse.field;

public interface ValueFieldFactory {

  static ValueFieldFactory getInstance() {
    return ValueFieldFactoryImpl.SINGLETON;
  }

  FieldValueSetter create(Class<?> fieldType);
}
