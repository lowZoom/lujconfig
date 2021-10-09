package luj.config.internal.container.typeglobal.add;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import luj.config.api.container.ConfigItem;
import luj.config.api.container.ConfigType;
import luj.config.api.container.TypeMap;
import luj.config.internal.container.item.add.ConfigValueConverter;

final class TypeMapGlobal implements TypeMap {

  @Override
  public void addItem(Map<String, Object> value, String idField) {
    Iterator<Object> iter = value.values().iterator();
    String fieldKey = (String) iter.next();
    Object fieldVal = iter.next();

    Object fieldKey2 = value.get(idField);
    checkArgument(fieldKey.equals(fieldKey2), "%s, %s", fieldKey, fieldKey2);

    Optional<Class<?>> targetType = _configType.getFields().stream()
        .filter(f -> f.getName().equals(fieldKey))
        .findAny()
        .map(f -> f.getType().asClass());

    Object newVal = !targetType.isPresent() ? fieldVal :
        ConfigValueConverter.GET.convert(fieldVal, targetType.get());

    _fieldMap.put(fieldKey.intern(), newVal);
  }

  @Override
  public ConfigItem findItem(Comparable<?> id) {
    return _item;
  }

  @Override
  public Collection<ConfigItem> getItems() {
    return ImmutableList.of(_item);
  }

  @Override
  public ConfigType getType() {
    return _configType;
  }

  @Override
  public boolean isGlobal() {
    return true;
  }

  ConfigType _configType;

  ConfigItem _item;
  Map<String, Object> _fieldMap;
}
