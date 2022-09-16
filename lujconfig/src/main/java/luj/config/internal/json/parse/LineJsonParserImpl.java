package luj.config.internal.json.parse;

import static com.google.common.base.Preconditions.checkNotNull;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import luj.config.internal.json.parse.field.FieldValueHandler;
import luj.config.internal.json.parse.field.FieldValueHandlerFactory;

final class LineJsonParserImpl implements LineJsonParser {

  LineJsonParserImpl(String jsonStr, Config config) {
    _jsonStr = jsonStr;
    _config = config;
  }

  @Override
  public Result parse() throws IOException, IllegalAccessException {
    Object configInstance = _config.createInstance();
    Class<?> implType = configInstance.getClass();
    JsonNode rootNode = JACKSON.readTree(_jsonStr);

    List<Field> fieldList = getterStream()
        .map(Method::getName)
        .map(n -> getField(implType, n))
        .collect(Collectors.toList());

    List<LinkableField> linkableList = new ArrayList<>();
    for (Field field : fieldList) {
      jsonToField(rootNode, field, configInstance, linkableList);
    }

    return new ResultImpl(configInstance, linkableList);
  }

  private Stream<Method> getterStream() {
    return Arrays.stream(_config.getConfigInterface().getMethods());
  }

  private Field getField(Class<?> implType, String fieldName) {
    try {
      Field field = implType.getDeclaredField(fieldName);
      field.setAccessible(true);
      return field;

    } catch (NoSuchFieldException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private void jsonToField(JsonNode json, Field field, Object configInstance,
      List<LinkableField> linkableList) throws IllegalAccessException {
    String fieldName = field.getName();
    JsonNode fieldNode = json.findValue(fieldName);

    Class<?> fieldType = field.getType();
    FieldValueHandler valueSetter = FieldValueHandlerFactory.getInstance().create(fieldType);
    checkNotNull(valueSetter, fieldType.getName());

    ContextImpl ctx = new ContextImpl(field, configInstance, fieldNode, linkableList);
    valueSetter.handle(ctx);
  }

  interface Config {

    Class<?> getConfigInterface();

    Object createInstance();
  }

  private static final ObjectMapper JACKSON = new ObjectMapper();

  private final String _jsonStr;
  private final Config _config;
}
