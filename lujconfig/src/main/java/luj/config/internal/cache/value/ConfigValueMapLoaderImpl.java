package luj.config.internal.cache.value;

import static com.google.common.collect.ImmutableMap.toImmutableMap;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import luj.config.internal.json.parse.LineJsonParser;

final class ConfigValueMapLoaderImpl implements ConfigValueMapLoader {

  ConfigValueMapLoaderImpl(List<ConfigFile> fileList) {
    _fileList = fileList;
  }

  @Override
  public Map<Class<?>, Map<String, Value>> load() {
    _fileList.stream()
        .filter(ConfigFile::isAbsent)
        .forEach(ConfigFile::logAbsent);

    Map<Class<?>, Map<String, Value>> result = _fileList.stream()
        .filter(f -> !f.isAbsent())
        .collect(toImmutableMap(ConfigFile::getConfigType, this::toIdMap));

    result.values().stream()
        .flatMap(m -> m.values().stream())
        .map(ValueImpl.class::cast)
        .forEach(v -> v.setValueMap(result));

    return result;
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
    List<ConfigValueMapLoader.LinkableField> linkableList = line.getLinkableList().stream()
        .map(LinkableFieldImpl::new)
        .collect(Collectors.toList());

    ValueImpl value = new ValueImpl(line.getInstance(), linkableList);
    linkableList.stream()
        .map(LinkableFieldImpl.class::cast)
        .forEach(f -> f.setParentValue(value));

    return value;
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

    List<LineJsonParser.LinkableField> getLinkableList();
  }

  private final List<ConfigFile> _fileList;
}
