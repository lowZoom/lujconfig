package luj.config.api.container;

import java.util.List;
import java.util.Optional;

public interface ConfigType {

  interface Field {

    interface TypeLike {

      Class<?> asClass();

      Field getParentField();
    }

    interface Type extends TypeLike {

      boolean isReference();

      Optional<CollectionType> asCollectionType();
    }

    interface CollectionType extends TypeLike {

      Type getElementType();

      int getDimension();
    }

    ////////////////////////////////

    String getName();

    Type getType();
  }

  List<Field> getFields();

  Class<?> asClass();
}
