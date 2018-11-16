package luj.config.internal.cache.link.field;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

final class JsonValueImpl implements ConfigLinkBehavior.JsonValue {

  JsonValueImpl(JsonNode jsonNode) {
    _jsonNode = jsonNode;
  }

  @Override
  public String asString() {
    return _jsonNode.asText();
  }

  @Override
  public List<String> asStringList() {
    return StreamSupport.stream(_jsonNode.spliterator(), false)
        .map(JsonNode::asText)
        .collect(Collectors.toList());
  }

  private final JsonNode _jsonNode;
}
