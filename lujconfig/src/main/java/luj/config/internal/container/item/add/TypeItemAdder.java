package luj.config.internal.container.item.add;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;
import luj.bean.api.BeanContext;
import luj.config.api.container.ConfigItem;
import luj.config.internal.container.type.factory.ConfigTypeFactory;

public enum TypeItemAdder {
  GET;

  public void add(Class<?> configType, Map<Comparable<?>, ConfigItem> itemAll,
      Map<String, Object> value, String idField, BeanContext lujbean) {
    ConfigItemImpl item = new ConfigItemImpl();

    Map<String, Object> newValue = ValueMapAligner.GET.align(value, configType);
    item._value = lujbean.createBean(configType, newValue);
    item._idField = idField;
    item._type = ConfigTypeFactory.GET.create(configType);
    item._lujbean = lujbean;

    Object id = newValue.get(idField);
    checkNotNull(id, "%s#%s", configType.getName(), idField);

    itemAll.put((Comparable<?>) id, item);
  }
}
