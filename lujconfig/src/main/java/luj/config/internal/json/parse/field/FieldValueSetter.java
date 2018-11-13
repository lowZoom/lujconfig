package luj.config.internal.json.parse.field;

import com.fasterxml.jackson.databind.JsonNode;
import java.lang.reflect.Field;

public interface FieldValueSetter {

  interface Context {

    Field getField();

    Object getObject();

    JsonNode getJsonNode();
  }

  void setValue(Context ctx) throws IllegalAccessException;
}
