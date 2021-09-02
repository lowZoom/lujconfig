package luj.config.internal.container.typeglobal.add;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import luj.config.api.container.ConfigItem;
import luj.config.api.container.TypeMap;

final class TypeMapGlobal implements TypeMap {

  @Override
  public void addItem(Map<String, Object> value, String idField) {
    Iterator<Object> iter = value.values().iterator();
    String fieldKey = (String) iter.next();
    Object fieldVal = iter.next();

    Object fieldKey2 = value.get(idField);
    checkArgument(fieldKey.equals(fieldKey2), "%s, %s", fieldKey, fieldKey2);

    //TODO: 转换类型

    _fieldMap.put(fieldKey, fieldVal);
  }

  @Override
  public ConfigItem findItem(Comparable<?> id) {
    return _item;
  }

  @Override
  public Collection<ConfigItem> getItems() {
    return ImmutableList.of(_item);
  }

  Class<?> _configType;

  ConfigItem _item;
  Map<String, Object> _fieldMap;
}
