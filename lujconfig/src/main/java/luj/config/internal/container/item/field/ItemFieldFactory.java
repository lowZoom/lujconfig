package luj.config.internal.container.item.field;

import luj.bean.api.bean.Bean;
import luj.config.api.container.ConfigItem;

public enum ItemFieldFactory {
  GET;

  public ConfigItem.Field create(Bean<?> item, String name) {
    ItemFieldImpl field = new ItemFieldImpl();
    field._itemValue = item;
    field._name = name;
    return field;
  }
}
