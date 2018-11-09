package luj.config.internal.cache;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

final class ConfigCacheLoaderImpl implements ConfigCacheLoader {

  ConfigCacheLoaderImpl(List<ConfigFile> fileList) {
    _fileList = fileList;
  }

  @Override
  public ConfigCache load() {
    _fileList.stream()
        .filter(ConfigFile::isAbsent)
        .forEach(ConfigFile::logAbsent);

    Map<Class<?>, Map<String, Object>> cacheMap = _fileList.stream()
        .filter(f -> !f.isAbsent())
        .collect(Collectors.toMap(ConfigFile::getConfigType, this::toIdMap));

    return new ConfigCacheImpl(cacheMap);
  }

  private Map<String, Object> toIdMap(ConfigFile file) {
    try {
      return file.readLines().stream()
          .collect(Collectors.toMap(ConfigLine::getId, ConfigLine::getValue));

    } catch (IOException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  interface ConfigFile {

    Class<?> getConfigType();

    boolean isAbsent();

    void logAbsent();

    List<ConfigLine> readLines() throws IOException;
  }

  interface ConfigLine {

    String getId();

    Object getValue();
  }

  private final List<ConfigFile> _fileList;
}
