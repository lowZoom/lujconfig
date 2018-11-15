package luj.config.internal.json.parse.field;

public interface FieldValueHandlerFactory {

  static FieldValueHandlerFactory getInstance() {
    return FieldValueHandlerFactoryImpl.SINGLETON;
  }

  FieldValueHandler create(Class<?> fieldType);
}
