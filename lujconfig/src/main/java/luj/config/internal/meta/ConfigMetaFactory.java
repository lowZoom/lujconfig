package luj.config.internal.meta;

import luj.ava.reflect.generic.GenericType;
import luj.config.anno.Config;
import luj.config.internal.meta.spring.ConfigConstructor;
import luj.config.internal.meta.spring.ConfigIdGetter;
import luj.config.internal.meta.spring.ConfigMetaHolder;

final class ConfigMetaFactory {

  ConfigMetaFactory(ConfigMetaHolder<?> metaHolder) {
    _metaHolder = metaHolder;
  }

  ConfigMeta create() {
    Class<?> configType = getConfigType();
    return new ConfigMetaImpl(configType, getConfigName(configType),
        getConfigConstructor(), getIdGetter());
  }

  private Class<?> getConfigType() {
    return GenericType.fromSubclass(_metaHolder.getClass()).getTypeArg(0);
  }

  private String getConfigName(Class<?> configType) {
    return configType.getAnnotation(Config.class).value();
  }

  @SuppressWarnings("unchecked")
  private ConfigConstructor<Object> getConfigConstructor() {
    return (ConfigConstructor<Object>) _metaHolder.getConstructor();
  }

  @SuppressWarnings("unchecked")
  private ConfigIdGetter<Object> getIdGetter() {
    return (ConfigIdGetter<Object>) _metaHolder.getIdGetter();
  }

  private final ConfigMetaHolder<?> _metaHolder;
}
