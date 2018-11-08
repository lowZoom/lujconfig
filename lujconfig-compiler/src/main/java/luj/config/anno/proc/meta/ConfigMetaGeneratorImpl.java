package luj.config.anno.proc.meta;

import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import javax.lang.model.element.Modifier;

final class ConfigMetaGeneratorImpl implements ConfigMetaGenerator {

  ConfigMetaGeneratorImpl(ConfigDeclaration configDeclaration) {
    _configDeclaration = configDeclaration;
  }

  @Override
  public void generate() throws IOException {
    String className = _configDeclaration.getClassName();

    TypeSpec metaClass = TypeSpec.classBuilder(className + "Meta")
        .addModifiers(Modifier.FINAL)
        .build();

    _configDeclaration.writeToFile(metaClass);
  }

  interface ConfigDeclaration {

    String getClassName();

    void writeToFile(TypeSpec type) throws IOException;
  }

  private final ConfigDeclaration _configDeclaration;
}
