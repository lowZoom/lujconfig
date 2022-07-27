package luj.config.internal.container.item.add;

import static com.google.common.base.Preconditions.checkArgument;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import luj.ava.collection.map.MapX;

/**
 * 把实际值的类型跟代码声明相对应
 */
enum ValueMapAligner {
  GET;

  Map<String, Object> align(Map<String, Object> input, Class<?> type) {
    checkArgument(type.isInterface(), type.getName());

    MapX.Builder<String, Object> result = MapX.builder();
    for (Method m : type.getMethods()) {
      String fieldName = m.getName();
      Object oldValue = input.get(fieldName);

      Object newValue = convertValue(oldValue, m.getReturnType());
      result.put(fieldName.intern(), newValue);
    }

    return result.build();
  }

  private Object convertValue(Object old, Class<?> newType) {
    if (old instanceof Collection) {
      return old;
    }
    return ConfigValueConverter.GET.convert(old, newType);
  }
}
