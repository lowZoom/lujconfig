package luj.config.internal.container.item.add;

import luj.bean.api.bean.ImmutableBean;
import luj.config.api.container.ConfigItem;

final class ConfigItemImpl implements ConfigItem {

  @SuppressWarnings("unchecked")
  @Override
  public <C> C getValue(Class<C> type) {
    return (C) _value.getValueInstance();
  }

  ImmutableBean<?> _value;
}
