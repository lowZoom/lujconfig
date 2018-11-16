package luj.config.internal.json.parse;

import com.fasterxml.jackson.databind.JsonNode;
import java.lang.reflect.Field;

final class LinkableFieldImpl implements LineJsonParser.LinkableField {

  LinkableFieldImpl(Field field, JsonNode jsonNode) {
    _field = field;
    _jsonNode = jsonNode;
  }

  @Override
  public Field getField() {
    return _field;
  }

  @Override
  public JsonNode getJsonValue() {
    return _jsonNode;
  }

  private final Field _field;

  private final JsonNode _jsonNode;
}
