package luj.config.internal.json.parse;

import com.fasterxml.jackson.databind.JsonNode;
import java.lang.reflect.Field;
import luj.config.internal.json.parse.field.FieldValueSetter;

final class FieldHolder implements FieldValueSetter.Context {

  FieldHolder(Field field, Object configInstance, JsonNode jsonNode, FieldValueSetter valueSetter) {
    _field = field;
    _configInstance = configInstance;

    _jsonNode = jsonNode;
    _valueSetter = valueSetter;
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

  FieldValueSetter getValueSetter() {
    return _valueSetter;
  }

  private final Field _field;
  private final Object _configInstance;

  private final JsonNode _jsonNode;
  private final FieldValueSetter _valueSetter;
}
