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
      return implType.getDeclaredField(fieldName);

    } catch (NoSuchFieldException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private void jsonToField(JsonNode json, Field field, Object configInstance,
      List<LinkableField> linkableList) throws IllegalAccessException {
    String fieldName = field.getName();
    JsonNode fieldNode = json.findValue(fieldName);

    boolean inaccessible = !field.isAccessible();
    if (inaccessible) {
      field.setAccessible(true);
    }

    Class<?> fieldType = field.getType();
    FieldValueHandler valueSetter = FieldValueHandlerFactory.getInstance().create(fieldType);
    checkNotNull(valueSetter, fieldType.getName());

    //TODO: 不只是把值设置进字段里，要把需要后续处理的数据打包带出来
    ContextImpl ctx = new ContextImpl(field, configInstance, fieldNode, linkableList);
    valueSetter.handle(ctx);

    if (inaccessible) {
      field.setAccessible(false);
    }
  }

  interface Config {

    Class<?> getConfigInterface();

    Object createInstance();
  }

  private static final ObjectMapper JACKSON = new ObjectMapper();

  private final String _jsonStr;

  private final Config _config;
}
