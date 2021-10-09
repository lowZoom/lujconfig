package luj.config.internal.container;

import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.config.api.container.ConfigType;

enum ConfigTypeNull implements ConfigType {
  INSTANCE;

  @Override
  public List<Field> getFields() {
    return ImmutableList.of();
  }

  @Override
  public Class<?> asClass() {
    return Void.class;
  }
}
