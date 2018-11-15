package luj.config.internal.cache.value;

import static com.google.common.collect.ImmutableMap.toImmutableMap;

import java.io.IOException;
import java.util.List;
import java.util.Map;

final class ConfigValueMapLoaderImpl implements ConfigValueMapLoader {

  ConfigValueMapLoaderImpl(List<ConfigFile> fileList) {
    _fileList = fileList;
  }

  @Override
  public ValueMap load() {
    _fileList.stream()
        .filter(ConfigFile::isAbsent)
        .forEach(ConfigFile::logAbsent);

    Map<Class<?>, Map<String, Value>> valueMap = _fileList.stream()
        .filter(f -> !f.isAbsent())
        .collect(toImmutableMap(ConfigFile::getConfigType, this::toIdMap));

    return new ValueMapImpl(valueMap);
  }

  private Map<String, Value> toIdMap(ConfigFile file) {
    try {
      return file.readLines().stream()
          .collect(toImmutableMap(ConfigLine::getId, this::toValue));

    } catch (IOException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private Value toValue(ConfigLine line) {
    return new ValueImpl(line.getInstance());
  }

  interface ConfigFile {

    Class<?> getConfigType();

    boolean isAbsent();

    void logAbsent();

    List<ConfigLine> readLines() throws IOException;
  }

  interface ConfigLine {

    String getId();

    Object getInstance();
  }

  private final List<ConfigFile> _fileList;
}
