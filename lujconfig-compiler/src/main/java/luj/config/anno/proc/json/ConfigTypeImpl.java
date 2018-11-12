package luj.config.anno.proc.json;

import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.lang.model.element.ExecutableElement;
import luj.generate.annotation.process.type.ProcType;

final class ConfigTypeImpl implements ConfigImplGeneratorImpl.ConfigType {

  ConfigTypeImpl(ProcType procType) {
    _procType = procType;
  }

  @Override
  public String getClassName() {
    return _procType.toElement().getSimpleName().toString();
  }

  @Override
  public TypeName toTypeName() {
    return _procType.toTypeName();
  }

  @Override
  public List<ConfigImplGeneratorImpl.Method> getMethodList() {
    return _procType.toElement().getEnclosedElements().stream()
        .map(ExecutableElement.class::cast)
        .map(MethodImpl::new)
        .collect(Collectors.toList());
  }

  @Override
  public void saveInSamePackage(TypeSpec type) throws IOException {
    _procType.getPackage().writeToFile(type);
  }

  private final ProcType _procType;
}
