package luj.config.internal.cache.link.field;

import java.util.List;

enum ConfigLinkBehaviorFactoryImpl implements ConfigLinkBehaviorFactory {
  SINGLETON;

  @Override
  public ConfigLinkBehavior create(Class<?> fieldType) {
    if (fieldType == List.class) {
      return LinkList.SINGLETON;
    }
    return LinkObject.SINGLETON;
  }
}
