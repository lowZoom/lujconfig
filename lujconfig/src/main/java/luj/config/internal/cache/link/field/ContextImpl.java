package luj.config.internal.cache.link.field;

import com.google.common.collect.ImmutableMap;
import java.lang.reflect.Field;
import java.util.Map;
import luj.config.internal.cache.value.ConfigValueMapLoader;

final class ContextImpl implements ConfigLinkBehavior.Context {

  ContextImpl(ConfigValueMapLoader.LinkableField parseField) {
    _parseField = parseField;

    _rawField = parseField.getField();
    _configValue = parseField.getParentValue();
  }

  @Override
  public ConfigLinkBehavior.JsonValue getJsonValue() {
    return new JsonValueImpl(_parseField.getJsonValue());
  }

  @Override
  public Field getField() {
    return _rawField;
  }

  @Override
  public Object findLinkInstance(Class<?> linkType, String id) {
    return getValueMap(linkType).get(id).getConfigInstance();
  }

  @Override
  public void setFieldValue(Object value) {
    try {
      _rawField.set(_configValue.getConfigInstance(), value);

    } catch (IllegalAccessException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private Map<String, ConfigValueMapLoader.Value> getValueMap(Class<?> linkType) {
    return _configValue.getParentMap().getOrDefault(linkType, ImmutableMap.of());
  }

  private final ConfigValueMapLoader.LinkableField _parseField;

  private final Field _rawField;
  private final ConfigValueMapLoader.Value _configValue;
}
