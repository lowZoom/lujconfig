package luj.config.internal.container.typeglobal.add;

import luj.bean.api.bean.Bean;
import luj.bean.api.bean.ImmutableBean;
import luj.config.api.container.ConfigItem;
import luj.config.api.container.ConfigType;
import luj.config.internal.container.item.field.ItemFieldFactory;

final class GlobalItemImpl implements ConfigItem {

  @Override
  public <C> C getValue(Class<C> type) {
//    Bean<C> newVal = _value.as(type);
//    _value = newVal;
//    return newVal.getValueInstance();
    return (C) _value.getValueInstance();
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

  ConfigType _type;
}
