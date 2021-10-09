package luj.config.internal.container.type.factory;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.stream.Collectors;
import luj.ava.reflect.type.TypeX;
import luj.config.api.container.ConfigType;
import luj.config.internal.container.type.factory.field.FieldTypeFactory;

public enum ConfigTypeFactory {
  GET;

  public ConfigType create(Class<?> configClass) {
    ConfigTypeImpl type = new ConfigTypeImpl();
    type._class = configClass;

    type._fieldList = Arrays.stream(configClass.getMethods())
        .map(this::createField)
        .collect(Collectors.toList());

    return type;
  }

  private ConfigType.Field createField(Method method) {
    ConfigFieldImpl field = new ConfigFieldImpl();
    field._name = method.getName();

    Type fieldType = method.getGenericReturnType();
    field._type = new FieldTypeFactory(TypeX.of(fieldType), field).create();

    return field;
  }
}
