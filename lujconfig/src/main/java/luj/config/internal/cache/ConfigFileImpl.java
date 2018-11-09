package luj.config.internal.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import luj.config.internal.meta.ConfigMeta;
import org.omg.CORBA.NO_IMPLEMENT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class ConfigFileImpl implements ConfigCacheLoaderImpl.ConfigFile {

  ConfigFileImpl(ConfigMeta configMeta, Path configPath) {
    _configMeta = configMeta;
    _configPath = configPath;
  }

  @Override
  public Class<?> getConfigType() {
    return _configMeta.getConfigType();
  }

  @Override
  public boolean isAbsent() {
    return !Files.exists(_configPath);
  }

  @Override
  public void logAbsent() {
    LOG.warn("找不到配置：{}", _configPath);
  }

  @Override
  public List<ConfigCacheLoaderImpl.ConfigLine> readLines() throws IOException {
    try (Stream<String> lines = Files.lines(_configPath)) {
      throw new NO_IMPLEMENT("readLines尚未实现");

      //return ConfigBodyReader.Factory.create(lines).read()
      //    .map(l -> new ConfigLineImpl(, _configMeta))
      //    .collect(Collectors.toList());
    }
  }

  private static final Logger LOG = LoggerFactory.getLogger(ConfigFileImpl.class);

  private final ConfigMeta _configMeta;
  private final Path _configPath;
}
