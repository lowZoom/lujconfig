package luj.config.internal.json.parse.field;

import com.fasterxml.jackson.databind.JsonNode;
import java.lang.reflect.Field;

enum FieldString implements FieldValueHandler {
  SINGLETON;

  @Override
  public void handle(Context ctx) throws IllegalAccessException {
    Field field = ctx.getField();
    Object configInstance = ctx.getObject();

    JsonNode jsonNode = ctx.getJsonNode();
    if (jsonNode == null) {
      field.set(configInstance, "");
      return;
    }

    String value = jsonNode.asText();
    field.set(configInstance, value);
  }
}
