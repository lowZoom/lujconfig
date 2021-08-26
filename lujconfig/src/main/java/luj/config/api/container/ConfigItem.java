package luj.config.api.container;

public interface ConfigItem {

  <C> C getValue(Class<C> type);
}
