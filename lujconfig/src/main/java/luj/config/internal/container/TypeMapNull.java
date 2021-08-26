package luj.config.internal.container;

import java.util.Map;
import luj.config.api.container.ConfigItem;
import luj.config.api.container.TypeMap;

enum TypeMapNull implements TypeMap {
  INSTANCE;

  @Override
  public void addItem(Map<String, Object> value, String idField) {
    throw new UnsupportedOperationException("See ConfigContainer#addType");
  }

  @Override
  public ConfigItem findItem(Comparable<?> id) {
    return null;
  }
}
