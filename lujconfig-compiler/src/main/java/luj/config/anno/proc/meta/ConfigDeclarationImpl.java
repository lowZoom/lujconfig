package luj.config.anno.proc.meta;

import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import luj.generate.annotation.process.type.ProcType;

final class ConfigDeclarationImpl implements ConfigMetaGeneratorImpl.ConfigDeclaration {

  ConfigDeclarationImpl(ProcType procType) {
    _procType = procType;
  }

  @Override
  public String getClassName() {
    return _procType.toElement().getSimpleName().toString();
  }

  @Override
  public void writeToFile(TypeSpec type) throws IOException {
    _procType.getPackage().writeToFile(type);
  }

  private final ProcType _procType;
}
