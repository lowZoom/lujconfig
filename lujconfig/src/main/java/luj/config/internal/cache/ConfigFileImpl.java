package luj.config.internal.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import luj.config.internal.json.file.ConfigBodyReader;
import luj.config.internal.json.parse.LineJsonParser;
import luj.config.internal.meta.ConfigMeta;
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
    LOG.warn("找不到配置：{}（{}）", _configPath, _configMeta.getConfigType().getSimpleName());
  }

  @Override
  public List<ConfigCacheLoaderImpl.ConfigLine> readLines() throws IOException {
    try (Stream<String> lines = Files.lines(_configPath)) {
      return ConfigBodyReader.Factory.create(lines).read()
          .map(this::createLine)
          .collect(Collectors.toList());
    }
  }

  private ConfigLineImpl createLine(String lineStr) {
    try {
      Object configInstance = LineJsonParser.Factory.create(lineStr, _configMeta).parse();
      return new ConfigLineImpl(configInstance, _configMeta);

    } catch (IOException | IllegalAccessException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private static final Logger LOG = LoggerFactory.getLogger(ConfigFileImpl.class);

  private final ConfigMeta _configMeta;
  private final Path _configPath;
}
