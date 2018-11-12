package luj.config.anno.proc.json;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import javax.lang.model.element.ExecutableElement;

final class MethodImpl implements ConfigImplGeneratorImpl.Method {

  MethodImpl(ExecutableElement elem) {
    _elem = elem;
  }

  @Override
  public String getName() {
    return _elem.getSimpleName().toString();
  }

  @Override
  public TypeName getReturnType() {
    return TypeName.get(_elem.getReturnType());
  }

  @Override
  public MethodSpec.Builder override() {
    return MethodSpec.overriding(_elem);
  }

  private final ExecutableElement _elem;
}
