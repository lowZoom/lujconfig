package luj.config.api.container;

import java.util.Collection;

public interface ConfigContainer {

  void addType(String typeKey, Class<?> type);

  void addGlobalType(String typeKey, Class<?> type);

  TypeMap findType(String typeKey);

  Collection<TypeMap> getAllTypes();
}
