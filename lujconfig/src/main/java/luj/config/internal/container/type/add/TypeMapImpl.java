package luj.config.internal.container.type.add;

import java.util.Collection;
import java.util.Map;
import luj.bean.api.BeanContext;
import luj.config.api.container.ConfigItem;
import luj.config.api.container.TypeMap;
import luj.config.internal.container.item.add.TypeItemAdder;

final class TypeMapImpl implements TypeMap {

  @Override
  public void addItem(Map<String, Object> value, String idField) {
    TypeItemAdder.GET.add(_configType, _itemAll, value, idField, _lujbean);
  }

  @Override
  public ConfigItem findItem(Comparable<?> id) {
    return _itemAll.get(id);
  }

  @Override
  public Collection<ConfigItem> getItems() {
    return _itemAll.values();
  }

  Class<?> _configType;
  Map<Comparable<?>, ConfigItem> _itemAll;

  BeanContext _lujbean;
}
