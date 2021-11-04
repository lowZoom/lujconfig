package luj.config.internal.container.item.add;

import luj.bean.api.BeanContext;
import luj.bean.api.bean.Bean;
import luj.bean.api.bean.ImmutableBean;
import luj.config.api.container.ConfigItem;
import luj.config.api.container.ConfigType;
import luj.config.internal.container.item.field.ItemFieldFactory;
import luj.config.internal.container.item.value.ValueCacheRefresher;

final class ConfigItemImpl implements ConfigItem {

  @Override
  public <C> C getValue(Class<C> type) {
    ImmutableBean<C> cache = ValueCacheRefresher.GET.refresh(_value, _valueCache, type, _lujbean);
    _valueCache = cache;
    return cache.getValueInstance();
  }

  @Override
  public Field getField(String name) {
    return ItemFieldFactory.GET.create(_value, name);
  }

  @Override
  public Field getIdField() {
    return ItemFieldFactory.GET.create(_value, _idField);
  }

  @Override
  public ConfigType getType() {
    return _type;
  }

  Bean<?> _value;
  ImmutableBean<?> _valueCache;

  String _idField;
  ConfigType _type;

  BeanContext _lujbean;
}
