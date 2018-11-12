package luj.config.internal.json.parse;

import java.io.IOException;
import luj.config.internal.meta.ConfigMeta;

public interface LineJsonParser {

  interface Factory {

    static LineJsonParser create(String jsonStr, ConfigMeta configMeta) {
      return new LineJsonParserImpl(jsonStr, new ConfigImpl(configMeta));
    }
  }

  Object parse() throws IOException, IllegalAccessException;
}
