package luj.config.internal.container.typeglobal.add;

import luj.bean.api.bean.Bean;
import luj.bean.api.bean.ImmutableBean;
import luj.config.api.container.ConfigItem;

final class GlobalItemImpl implements ConfigItem {

  @Override
  public <C> C getValue(Class<C> type) {
//    Bean<C> newVal = _value.as(type);
//    _value = newVal;
//    return newVal.getValueInstance();
    return (C) _value.getValueInstance();
  }

  Bean<?> _value;
}
