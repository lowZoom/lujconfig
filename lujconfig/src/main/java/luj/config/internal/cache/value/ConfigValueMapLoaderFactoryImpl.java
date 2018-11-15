package luj.config.internal.cache.value;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import luj.ava.spring.Internal;
import luj.config.internal.meta.ConfigMeta;
import luj.config.internal.meta.ConfigMetaCollector;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class ConfigValueMapLoaderFactoryImpl implements ConfigValueMapLoader.Factory {

  @Override
  public ConfigValueMapLoader create() {
    List<ConfigValueMapLoaderImpl.ConfigFile> fileList = _configMetaCollector.collect().stream()
        .map(m -> new ConfigFileImpl(m, getConfigPath(m)))
        .collect(Collectors.toList());

    return new ConfigValueMapLoaderImpl(fileList);
  }

  private Path getConfigPath(ConfigMeta meta) {
    //TODO: 变成非写死
    return Paths.get("dat", "json", meta.getConfigName() + ".json").toAbsolutePath();
  }

  @Autowired
  private ConfigMetaCollector _configMetaCollector;
}
