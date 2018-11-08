package luj.config.internal.meta.spring;

public interface ConfigIdGetter<T> {

  String getId(T config);
}
