package luj.config.internal.json.parse;

import com.fasterxml.jackson.databind.JsonNode;
import java.lang.reflect.Field;
import java.util.List;
import luj.config.internal.json.parse.field.FieldValueHandler;

final class ContextImpl implements FieldValueHandler.Context {

  ContextImpl(Field field, Object configInstance, JsonNode jsonNode,
      List<LineJsonParser.LinkableField> linkableList) {
    _field = field;
    _configInstance = configInstance;

    _jsonNode = jsonNode;
    _linkableList = linkableList;
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

  @Override
  public void markLinkable() {
    _linkableList.add(new LinkableFieldImpl(_field, _jsonNode));
  }

  private final Field _field;
  private final Object _configInstance;

  private final JsonNode _jsonNode;
  private final List<LineJsonParser.LinkableField> _linkableList;
}
