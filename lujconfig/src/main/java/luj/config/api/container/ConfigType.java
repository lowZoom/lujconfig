package luj.config.api.container;

import java.util.List;

public interface ConfigType {

  interface Field {

    interface Type {

      boolean isReference();

      Class<?> asClass();
    }

    String getName();

    Type getType();
  }

  List<Field> getFields();
}
