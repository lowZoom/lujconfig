package luj.config.internal.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
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
  public List<ConfigCacheLoaderImpl.ConfigLine> readLines() {
    throw new NO_IMPLEMENT("readLines尚未实现");
  }

  private static final Logger LOG = LoggerFactory.getLogger(ConfigFileImpl.class);

  private final ConfigMeta _configMeta;

  private final Path _configPath;
}
