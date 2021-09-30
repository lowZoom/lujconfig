package luj.config.internal.container.item.field;

import luj.bean.api.bean.Bean;
import luj.config.api.container.ConfigItem;

final class ItemFieldImpl implements ConfigItem.Field {

  @Override
  public String getName() {
    return _name;
  }

  @Override
  public Object getValue() {
    return _itemValue.getField(_name);
  }

  @Override
  public void setValue(Object value) {
    _itemValue.setField(_name, value);
  }

  Bean<?> _itemValue;

  String _name;
}
