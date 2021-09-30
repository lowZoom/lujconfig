package luj.config.internal.container.type.factory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;
import luj.config.api.container.ConfigType;

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

    ConfigFieldTypeImpl fieldType = new ConfigFieldTypeImpl();
    fieldType._class = method.getReturnType();
    field._type = fieldType;

    return field;
  }
}
