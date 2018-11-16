package luj.config.internal.cache.value;

import java.util.List;
import luj.config.internal.json.parse.LineJsonParser;
import luj.config.internal.meta.ConfigMeta;

final class ConfigLineImpl implements ConfigValueMapLoaderImpl.ConfigLine {

  ConfigLineImpl(LineJsonParser.Result parseResult, ConfigMeta configMeta) {
    _parseResult = parseResult;
    _configImpl = parseResult.getConfigInstance();

    _configMeta = configMeta;
  }

  @Override
  public String getId() {
    return _configMeta.getConfigId(_configImpl);
  }

  @Override
  public Object getInstance() {
    return _parseResult.getConfigInstance();
  }

  @Override
  public List<LineJsonParser.LinkableField> getLinkableList() {
    return _parseResult.getLinkableList();
  }

  private final LineJsonParser.Result _parseResult;
  private final Object _configImpl;

  private final ConfigMeta _configMeta;
}
