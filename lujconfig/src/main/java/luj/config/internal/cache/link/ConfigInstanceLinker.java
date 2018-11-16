package luj.config.internal.cache.link;

import java.util.Map;
import luj.config.internal.cache.value.ConfigValueMapLoader;

public interface ConfigInstanceLinker {

  interface Factory {

    static Factory getInstance() {
      return ConfigInstanceLinkerFactoryImpl.SINGLETON;
    }

    ConfigInstanceLinker create(Map<Class<?>, Map<String, ConfigValueMapLoader.Value>> valueMap);
  }

  Map<Class<?>, Map<String, Object>> link();
}
