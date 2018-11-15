package luj.config.internal.json.parse.field;

import com.fasterxml.jackson.databind.JsonNode;
import java.lang.reflect.Field;

public interface FieldValueHandler {

  interface Context {

    Field getField();

    Object getObject();

    JsonNode getJsonNode();

    void markLinkable();
  }

  void handle(Context ctx) throws IllegalAccessException;
}
