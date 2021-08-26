package luj.config.internal.cache;


/**
 * @see luj.config.api.container.ConfigContainer
 */
@Deprecated
public interface ConfigCache {

  <T> T get(Class<T> configType, String id);
}
