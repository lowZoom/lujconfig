package luj.config.internal.container.type.add;

import java.util.HashMap;
import java.util.Map;
import luj.bean.api.BeanContext;
import luj.config.api.container.TypeMap;

public class ConfigTypeAdder {

  public ConfigTypeAdder(Map<String, TypeMap> typeAll, String typeKey, Class<?> type,
      BeanContext lujbean) {
    _typeAll = typeAll;
    _typeKey = typeKey;
    _type = type;
    _lujbean = lujbean;
  }

  public void add() {
    TypeMapImpl type = new TypeMapImpl();
    type._itemAll = new HashMap<>();

    type._configType = _type;
    type._lujbean = _lujbean;

    _typeAll.put(_typeKey, type);
  }

  private final Map<String, TypeMap> _typeAll;

  private final String _typeKey;
  private final Class<?> _type;

  private final BeanContext _lujbean;
}
