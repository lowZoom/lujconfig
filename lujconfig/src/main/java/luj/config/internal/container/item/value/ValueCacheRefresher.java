package luj.config.internal.container.item.value;

import java.util.Map;
import luj.bean.api.BeanContext;
import luj.bean.api.bean.Bean;
import luj.bean.api.bean.ImmutableBean;

public enum ValueCacheRefresher {
  GET;

  public <C> ImmutableBean<C> refresh(Bean<?> value, ImmutableBean<?> oldCache, Class<C> newType,
      BeanContext lujbean) {
    if (oldCache == null) {
      Map<String, Object> valueMap = value.getFieldMap();
      return lujbean.createImmutable(newType, valueMap);
    }

    return oldCache.as(newType);
  }
}
