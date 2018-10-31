package luj.config.internal.cache;

public interface ConfigCacheLoader {

  interface Factory {

    ConfigCacheLoader create();
  }

  ConfigCache load();
}
