package luj.config.internal.container;

import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.Map;
import luj.config.api.container.ConfigItem;
import luj.config.api.container.ConfigType;
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

  @Override
  public Collection<ConfigItem> getItems() {
    return ImmutableList.of();
  }

  @Override
  public ConfigType getType() {
    return ConfigTypeNull.INSTANCE;
  }

  @Override
  public boolean isAbsent() {
    return true;
  }
}
