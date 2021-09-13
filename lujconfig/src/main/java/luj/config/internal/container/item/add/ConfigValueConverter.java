package luj.config.internal.container.item.add;

import com.google.common.base.Defaults;

public enum ConfigValueConverter {
  GET;

  public Object convert(Object old, Class<?> newType) {
    if (old instanceof String) {
      return strTo((String) old, newType);
    }
    if (old == null) {
      return Defaults.defaultValue(newType);
    }
    throw new UnsupportedOperationException("未知类型：" + old.getClass().getName());
  }

//  private Object intTo(Integer val, Class<?> newType) {
//    if (newType == Integer.class || newType == int.class) {
//      return val;
//    }
//
//  }

  private Object strTo(String val, Class<?> newType) {
    if (newType == Integer.class || newType == int.class) {
      return Integer.valueOf(val);
    }
    return val;
  }
}
