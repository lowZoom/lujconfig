package luj.config.internal.cache.link.field;

public interface ConfigLinkBehaviorFactory {

  static ConfigLinkBehaviorFactory getInstance() {
    return ConfigLinkBehaviorFactoryImpl.SINGLETON;
  }

  ConfigLinkBehavior create(Class<?> fieldType);
}
