package luj.config.internal.cache;

public interface ConfigCache {

  <T> T get(Class<T> configType, String id);
}
