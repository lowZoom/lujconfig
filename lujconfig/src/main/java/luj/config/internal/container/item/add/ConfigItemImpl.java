package luj.config.internal.container.item.add;

import luj.bean.api.bean.Bean;
import luj.config.api.container.ConfigItem;
import luj.config.api.container.ConfigType;
import luj.config.internal.container.item.field.ItemFieldFactory;

final class ConfigItemImpl implements ConfigItem {

  @Override
  public <C> C getValue(Class<C> type) {
//    ImmutableBean<C> newVal = _value.as(type);
//    _value = newVal;
//    return newVal.getValueInstance();
    return (C) _value.getValueInstance();
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
  String _idField;

  ConfigType _type;
}
