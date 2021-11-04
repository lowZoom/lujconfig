package luj.config.internal.container.typeglobal.add;

import luj.bean.api.BeanContext;
import luj.bean.api.bean.Bean;
import luj.bean.api.bean.ImmutableBean;
import luj.config.api.container.ConfigItem;
import luj.config.api.container.ConfigType;
import luj.config.internal.container.item.field.ItemFieldFactory;
import luj.config.internal.container.item.value.ValueCacheRefresher;

final class GlobalItemImpl implements ConfigItem {

  @Override
  public <C> C getValue(Class<C> type) {
    ImmutableBean<C> cache = ValueCacheRefresher.GET.refresh(_value, _valueCache, type, _lujbean);
    _valueCache = cache;
    return cache.getValueInstance();
  }

  @Override
  public ConfigType getType() {
    return _type;
  }

  @Override
  public Field getField(String name) {
    return ItemFieldFactory.GET.create(_value, name);
  }

  @Override
  public Field getIdField() {
    return null;
  }

  Bean<?> _value;
  ImmutableBean<?> _valueCache;

  ConfigType _type;
  BeanContext _lujbean;
}
