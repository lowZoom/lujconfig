package luj.config.internal.cache.value;

import com.fasterxml.jackson.databind.JsonNode;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public interface ConfigValueMapLoader {

  interface Factory {

    ConfigValueMapLoader create();
  }

  interface Value {

    Object getConfigInstance();

    List<LinkableField> getLinkableList();

    Map<Class<?>, Map<String, Value>> getParentMap();
  }

  interface LinkableField {

    JsonNode getJsonValue();

    Field getField();

    Value getParentValue();
  }

  Map<Class<?>, Map<String, Value>> load();
}
