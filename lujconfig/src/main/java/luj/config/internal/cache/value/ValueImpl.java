package luj.config.internal.cache.value;

import java.util.List;
import java.util.Map;

final class ValueImpl implements ConfigValueMapLoader.Value {

  ValueImpl(Object configInstance, List<ConfigValueMapLoader.LinkableField> linkableList) {
    _configInstance = configInstance;
    _linkableList = linkableList;
  }

  @Override
  public Object getConfigInstance() {
    return _configInstance;
  }

  @Override
  public List<ConfigValueMapLoader.LinkableField> getLinkableList() {
    return _linkableList;
  }

  @Override
  public Map<Class<?>, Map<String, ConfigValueMapLoader.Value>> getParentMap() {
    return _valueMap;
  }

  void setValueMap(Map<Class<?>, Map<String, ConfigValueMapLoader.Value>> valueMap) {
    _valueMap = valueMap;
  }

  private final Object _configInstance;
  private final List<ConfigValueMapLoader.LinkableField> _linkableList;

  private Map<Class<?>, Map<String, ConfigValueMapLoader.Value>> _valueMap;
}
