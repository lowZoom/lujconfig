package luj.config.internal.cache.value;

final class ValueImpl implements ConfigValueMapLoader.Value {

  ValueImpl(Object configInstance) {
    _configInstance = configInstance;
  }

  @Override
  public Object getConfigInstance() {
    return _configInstance;
  }

  private final Object _configInstance;
}
