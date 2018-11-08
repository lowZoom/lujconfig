package luj.config.anno.proc.idget;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import javax.lang.model.element.Modifier;
import luj.config.internal.meta.spring.ConfigIdGetter;
import luj.generate.annotation.process.type.ProcType;
import org.springframework.stereotype.Component;

final class ConfigIdGetGeneratorImpl implements ConfigIdGetGenerator {

  ConfigIdGetGeneratorImpl(ProcType procType) {
    _procType = procType;
  }

  @Override
  public void generate() throws IOException {
    String configType = _procType.toElement().getSimpleName().toString();

    TypeSpec getterClass = TypeSpec.classBuilder(configType + "IdGet")
        .addModifiers(Modifier.FINAL)
        .addSuperinterface(getGetterClass())
        .addAnnotation(Component.class)
        .addMethod(makeGetId())
        .build();

    _procType.getPackage().writeToFile(getterClass);
  }

  private TypeName getGetterClass() {
    return ParameterizedTypeName.get(ClassName.get(ConfigIdGetter.class), _procType.toTypeName());
  }

  private MethodSpec makeGetId() {
    String param = "config";
    return MethodSpec.methodBuilder("getId")
        .addModifiers(Modifier.PUBLIC)
        .returns(String.class)
        .addParameter(_procType.toTypeName(), param)
        .addAnnotation(Override.class)
        .addStatement("return $L.id()", param)
        .build();
  }

  private final ProcType _procType;
}
