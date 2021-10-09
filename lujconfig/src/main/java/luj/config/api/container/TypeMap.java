package luj.config.api.container;

import java.util.Collection;
import java.util.Map;

public interface TypeMap {

  void addItem(Map<String, Object> value, String idField);

  ConfigItem findItem(Comparable<?> id);

  Collection<ConfigItem> getItems();

  ConfigType getType();

  default boolean isGlobal() {
    return false;
  }

  default boolean isAbsent() {
    return false;
  }
}
