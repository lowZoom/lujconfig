package luj.config.internal.container;

import java.util.Collection;
import java.util.Map;
import luj.bean.api.BeanContext;
import luj.config.api.container.ConfigContainer;
import luj.config.api.container.TypeMap;
import luj.config.internal.container.type.add.ConfigTypeAdder;
import luj.config.internal.container.typeglobal.add.GlobalTypeAdder;

final class ConfigContainerImpl implements ConfigContainer {

  @Override
  public void addType(String typeKey, Class<?> type) {
    new ConfigTypeAdder(_typeAll, typeKey, type, _lujbean).add();
  }

  @Override
  public void addGlobalType(String typeKey, Class<?> type) {
    new GlobalTypeAdder(_typeAll, typeKey, type, _lujbean).add();
  }

  @Override
  public TypeMap findType(String typeKey) {
    return _typeAll.getOrDefault(typeKey, TypeMapNull.INSTANCE);
  }

  @Override
  public Collection<TypeMap> getAllTypes() {
    return _typeAll.values();
  }

  Map<String, TypeMap> _typeAll;

  BeanContext _lujbean;
}
