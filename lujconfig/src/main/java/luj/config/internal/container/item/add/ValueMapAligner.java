package luj.config.internal.container.item.add;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Defaults;
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
    return ConfigValueConverter.GET.convert(old, newType);
  }
}
