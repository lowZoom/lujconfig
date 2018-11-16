package luj.config.internal.cache.link;

import java.util.List;
import java.util.stream.Collectors;
import luj.config.internal.cache.value.ConfigValueMapLoader;

final class ConfigValueImpl implements ConfigInstanceLinkerImpl.ConfigValue {

  ConfigValueImpl(ConfigValueMapLoader.Value configValue) {
    _configValue = configValue;
  }

  @Override
  public List<ConfigInstanceLinkerImpl.LinkableField> getLinkableList() {
    return _configValue.getLinkableList().stream()
        .map(LinkableFieldImpl::new)
        .collect(Collectors.toList());
  }

  @Override
  public Object getConfigInstance() {
    return _configValue.getConfigInstance();
  }

  private final ConfigValueMapLoader.Value _configValue;
}
