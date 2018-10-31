package luj.config.internal.cache;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import luj.ava.spring.Internal;
import luj.config.internal.meta.ConfigMetaCollector;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ConfigCacheLoaderFactoryImpl implements ConfigCacheLoader.Factory {

  @Override
  public ConfigCacheLoader create() {
    List<ConfigCacheLoaderImpl.ConfigFile> fileList = _configMetaCollector
        .collect().stream()
        .map(m -> new ConfigFileImpl(m, Paths.get(m.getConfigName())))
        .collect(Collectors.toList());

    return new ConfigCacheLoaderImpl(fileList);
  }

  @Autowired
  private ConfigMetaCollector _configMetaCollector;
}
