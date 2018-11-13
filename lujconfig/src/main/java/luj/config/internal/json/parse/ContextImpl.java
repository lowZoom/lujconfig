package luj.config.internal.json.parse;

import com.fasterxml.jackson.databind.JsonNode;
import java.lang.reflect.Field;
import luj.config.internal.json.parse.field.FieldValueSetter;

final class ContextImpl implements FieldValueSetter.Context {

  ContextImpl(Field field, Object configInstance, JsonNode jsonNode) {
    _field = field;
    _configInstance = configInstance;

    _jsonNode = jsonNode;
  }

  @Override
  public Field getField() {
    return _field;
  }

  @Override
  public Object getObject() {
    return _configInstance;
  }

  @Override
  public JsonNode getJsonNode() {
    return _jsonNode;
  }

  private final Field _field;
  private final Object _configInstance;

  private final JsonNode _jsonNode;
}
