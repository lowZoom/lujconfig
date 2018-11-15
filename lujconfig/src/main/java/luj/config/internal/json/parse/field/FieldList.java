package luj.config.internal.json.parse.field;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableList;
import java.lang.reflect.Field;
import luj.ava.reflect.generic.GenericType;

enum FieldList implements FieldValueHandler {
  SINGLETON;

  @Override
  public void handle(Context ctx) throws IllegalAccessException {
    JsonNode arrayNode = ctx.getJsonNode();
    Field field = ctx.getField();

    if (arrayNode == null || arrayNode.size() <= 0) {
      field.set(ctx.getObject(), ImmutableList.of());
      return;
    }

    GenericType fieldType = GenericType.ofField(field);
    Class<?> listType = fieldType.getTypeArg(0);

    ctx.markLinkable();
  }
}
