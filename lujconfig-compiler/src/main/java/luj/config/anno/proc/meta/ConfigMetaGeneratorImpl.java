package luj.config.anno.proc.meta;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import javax.lang.model.element.Modifier;
import luj.config.internal.meta.spring.ConfigMetaHolder;
import org.springframework.stereotype.Component;

final class ConfigMetaGeneratorImpl implements ConfigMetaGenerator {

  ConfigMetaGeneratorImpl(ConfigDeclaration configDeclaration) {
    _configDeclaration = configDeclaration;
  }

  @Override
  public void generate() throws IOException {
    String className = _configDeclaration.getClassName();

    TypeSpec metaClass = TypeSpec.classBuilder(className + "Meta")
        .addModifiers(Modifier.FINAL)
        .superclass(getHolderClass())
        .addAnnotation(Component.class)
        .build();

    _configDeclaration.writeToFile(metaClass);
  }

  private TypeName getHolderClass() {
    return ParameterizedTypeName.get(ClassName
        .get(ConfigMetaHolder.class), _configDeclaration.toTypeName());
  }

  interface ConfigDeclaration {

    String getClassName();

    TypeName toTypeName();

    void writeToFile(TypeSpec type) throws IOException;
  }

  private final ConfigDeclaration _configDeclaration;
}
