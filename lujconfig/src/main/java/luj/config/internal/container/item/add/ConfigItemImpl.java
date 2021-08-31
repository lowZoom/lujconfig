package luj.config.internal.container.item.add;

import luj.bean.api.bean.ImmutableBean;
import luj.config.api.container.ConfigItem;

final class ConfigItemImpl implements ConfigItem {

  @Override
  public <C> C getValue(Class<C> type) {
    ImmutableBean<C> newVal = _value.as(type);
    _value = newVal;
    return newVal.getValueInstance();
  }

  ImmutableBean<?> _value;
}
