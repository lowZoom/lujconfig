package luj.config.internal.container.typeglobal.add;

import java.util.Map;
import luj.bean.api.BeanContext;
import luj.bean.api.bean.Bean;
import luj.config.api.container.TypeMap;
import luj.config.internal.container.type.factory.ConfigTypeFactory;

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
    typeMap._configType = ConfigTypeFactory.GET.create(_type);

    GlobalItemImpl item = new GlobalItemImpl();
    Bean<?> valueBean = _lujbean.createBean(_type);
    item._value = valueBean;
    item._type = ConfigTypeFactory.GET.create(_type);
    item._lujbean = _lujbean;

    typeMap._item = item;
    typeMap._fieldMap = valueBean.getFieldMap();

    _typeAll.put(_typeKey, typeMap);
  }

  private final Map<String, TypeMap> _typeAll;

  private final String _typeKey;
  private final Class<?> _type;

  private final BeanContext _lujbean;
}
