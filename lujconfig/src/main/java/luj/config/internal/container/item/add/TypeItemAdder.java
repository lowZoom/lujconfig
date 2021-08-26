package luj.config.internal.container.item.add;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;
import luj.bean.api.BeanContext;
import luj.config.api.container.ConfigItem;

public enum TypeItemAdder {
  GET;

  public void add(Class<?> configType, Map<Comparable<?>, ConfigItem> itemAll,
      Map<String, Object> value, String idField, BeanContext lujbean) {
    ConfigItemImpl item = new ConfigItemImpl();

    Map<String, Object> newValue = ValueMapAligner.GET.align(value, configType);
    item._value = lujbean.createImmutable(configType, newValue);

    Object id = newValue.get(idField);
    checkNotNull(id, idField);

    itemAll.put((Comparable<?>) id, item);
  }
}
