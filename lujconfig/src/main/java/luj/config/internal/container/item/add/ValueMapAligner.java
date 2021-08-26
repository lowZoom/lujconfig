package luj.config.internal.container.item.add;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.collect.ImmutableMap;
import java.lang.reflect.Method;
import java.util.Map;

enum ValueMapAligner {
  GET;

  Map<String, Object> align(Map<String, Object> input, Class<?> type) {
    checkArgument(type.isInterface(), type.getName());

    ImmutableMap.Builder<String, Object> result = ImmutableMap.builder();
    for (Method m : type.getMethods()) {
      String fieldName = m.getName();
      Object oldValue = input.get(fieldName);

      result.put(fieldName.intern(), convertValue(oldValue, m.getReturnType()));
    }

    return result.build();
  }

  private Object convertValue(Object old, Class<?> newType) {
    if (old instanceof String) {
      return strTo((String) old, newType);
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
