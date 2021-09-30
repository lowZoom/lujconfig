package luj.config.api.container;

public interface ConfigItem {

  interface Field {

    String getName();

    Object getValue();

    void setValue(Object value);
  }

  <C> C getValue(Class<C> type);

  Field getField(String name);

  Field getIdField();

  ConfigType getType();
}
