package luj.config.internal.cache.link.field;

import java.lang.reflect.Field;
import java.util.List;
import luj.config.internal.cache.value.ConfigValueMapLoader;

public interface ConfigLinkBehavior {

  interface ContextFactory {

    static Context create(ConfigValueMapLoader.LinkableField parseField) {
      return new ContextImpl(parseField);
    }
  }

  interface Context {

    JsonValue getJsonValue();

    Field getField();

    Object findLinkInstance(Class<?> linkType, String id);

    void setFieldValue(Object value);
  }

  interface JsonValue {

    String asString();

    List<String> asStringList();
  }

  void link(Context ctx);
}
