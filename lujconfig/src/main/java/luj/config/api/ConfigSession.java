package luj.config.api;

public interface ConfigSession {

  <T> T findConfig(Class<T> configType, String id);
}
