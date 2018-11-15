package luj.config.internal.json.parse;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableList;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

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
  public List<String> getIdList() {
    return ImmutableList.copyOf(_jsonNode.iterator()).stream()
        .map(JsonNode::asText)
        .collect(Collectors.toList());
  }

  private final Field _field;

  private final JsonNode _jsonNode;
}
