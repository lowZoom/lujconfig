package luj.config.internal.meta;

public interface ConfigMeta {

  Class<?> getConfigType();

  String getConfigName();

  String getConfigId(Object config);
}
