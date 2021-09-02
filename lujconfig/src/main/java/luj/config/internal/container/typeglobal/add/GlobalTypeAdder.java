package luj.config.internal.container.typeglobal.add;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import luj.bean.api.BeanContext;
import luj.bean.api.bean.Bean;
import luj.bean.api.bean.ImmutableBean;
import luj.config.api.container.TypeMap;

public class GlobalTypeAdder {

  public GlobalTypeAdder(Map<String, TypeMap> typeAll, String typeKey, Class<?> type,
      BeanContext lujbean) {
    _typeAll = typeAll;
    _typeKey = typeKey;
    _type = type;
    _lujbean = lujbean;
  }

  public void add() {
    TypeMapGlobal typeMap = new TypeMapGlobal();
    typeMap._configType = _type;

    GlobalItemImpl item = new GlobalItemImpl();
    Bean<?> valueBean = _lujbean.createBean(_type);
    item._value = valueBean;

    typeMap._item = item;
    typeMap._fieldMap = valueBean.getFieldMap();

    _typeAll.put(_typeKey, typeMap);
  }

  private final Map<String, TypeMap> _typeAll;

  private final String _typeKey;
  private final Class<?> _type;

  private final BeanContext _lujbean;
}
