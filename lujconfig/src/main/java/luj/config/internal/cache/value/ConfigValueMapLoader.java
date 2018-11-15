package luj.config.internal.cache.value;

public interface ConfigValueMapLoader {

  interface Factory {

    ConfigValueMapLoader create();
  }

  interface ValueMap {

    Value get(Class<?> configType, String id);
  }

  interface Value {

    Object getConfigInstance();
  }

  ValueMap load();
}
