package luj.config.internal.container.typeglobal.add;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.collect.ImmutableList;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import luj.config.api.container.ConfigItem;
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

    Optional<Class<?>> targetType = Arrays.stream(_configType.getMethods())
        .filter(m -> m.getName().equals(fieldKey))
        .findAny()
        .map(Method::getReturnType);

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
  public boolean isGlobal() {
    return true;
  }

  Class<?> _configType;

  ConfigItem _item;
  Map<String, Object> _fieldMap;
}
