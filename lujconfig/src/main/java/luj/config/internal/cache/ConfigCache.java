package luj.config.internal.cache;

public interface ConfigCache {

  interface Factory {

    ConfigCache create();
  }

  <T> T get(Class<T> configType, String id);
}
