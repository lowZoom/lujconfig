package luj.config.internal.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import luj.config.internal.meta.ConfigMeta;
import org.omg.CORBA.NO_IMPLEMENT;

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
    System.out.println("找不到：" + _configPath);
  }

  @Override
  public List<ConfigCacheLoaderImpl.ConfigLine> readLines() {
    throw new NO_IMPLEMENT("readLines尚未实现");
  }

  private final ConfigMeta _configMeta;

  private final Path _configPath;
}
