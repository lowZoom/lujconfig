package luj.config.internal.cache.value;

import com.fasterxml.jackson.databind.JsonNode;
import java.lang.reflect.Field;
import luj.config.internal.json.parse.LineJsonParser;

final class LinkableFieldImpl implements ConfigValueMapLoader.LinkableField {

  LinkableFieldImpl(LineJsonParser.LinkableField parseField) {
    _parseField = parseField;
  }

  @Override
  public JsonNode getJsonValue() {
    return _parseField.getJsonValue();
  }

  @Override
  public Field getField() {
    return _parseField.getField();
  }

  @Override
  public ConfigValueMapLoader.Value getParentValue() {
    return _parentValue;
  }

  void setParentValue(ConfigValueMapLoader.Value parentValue) {
    _parentValue = parentValue;
  }

  private final LineJsonParser.LinkableField _parseField;

  private ConfigValueMapLoader.Value _parentValue;
}
