package luj.config.internal.meta;

public interface ConfigMeta {

  Class<?> getConfigType();

  String getConfigName();

  Object createConfigInstance();

  String getConfigId(Object config);
}
