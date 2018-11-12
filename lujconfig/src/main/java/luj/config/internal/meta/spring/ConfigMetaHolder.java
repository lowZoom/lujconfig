package luj.config.internal.meta.spring;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class ConfigMetaHolder<T> {

  public final ConfigConstructor<T> getConstructor() {
    return _constructor;
  }

  public final ConfigIdGetter<T> getIdGetter() {
    return _idGetter;
  }

  @Autowired
  private ConfigConstructor<T> _constructor;

  @Autowired
  private ConfigIdGetter<T> _idGetter;
}
