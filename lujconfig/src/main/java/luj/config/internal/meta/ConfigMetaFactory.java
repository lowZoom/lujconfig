package luj.config.internal.meta;

import luj.ava.reflect.generic.GenericType;
import luj.config.anno.Config;
import luj.config.internal.meta.spring.ConfigMetaHolder;

final class ConfigMetaFactory {

  ConfigMetaFactory(ConfigMetaHolder<?> metaHolder) {
    _metaHolder = metaHolder;
  }

  ConfigMeta create() {
    Class<?> configType = getConfigType();
    return new ConfigMetaImpl(configType, getConfigName(configType));
  }

  private Class<?> getConfigType() {
    return GenericType.fromSubclass(_metaHolder.getClass()).getTypeArg(0);
  }

  private String getConfigName(Class<?> configType) {
    Config anno = configType.getAnnotation(Config.class);
    return anno.value();
  }

  private final ConfigMetaHolder<?> _metaHolder;
}
