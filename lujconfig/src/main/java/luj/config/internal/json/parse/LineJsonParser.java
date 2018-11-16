package luj.config.internal.json.parse;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import luj.config.internal.meta.ConfigMeta;

public interface LineJsonParser {

  interface Factory {

    static LineJsonParser create(String jsonStr, ConfigMeta configMeta) {
      return new LineJsonParserImpl(jsonStr, new ConfigImpl(configMeta));
    }
  }

  interface Result {

    Object getConfigInstance();

    List<LinkableField> getLinkableList();
  }

  interface LinkableField {

    Field getField();

    JsonNode getJsonValue();
  }

  Result parse() throws IOException, IllegalAccessException;
}
