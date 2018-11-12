package luj.config.anno.proc.json;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.lang.model.element.Modifier;
import luj.config.internal.meta.spring.ConfigConstructor;
import org.springframework.stereotype.Component;

final class ConfigImplGeneratorImpl implements ConfigImplGenerator {

  ConfigImplGeneratorImpl(ConfigType configType) {
    _configType = configType;
  }

  @Override
  public void generate() throws IOException {
    String configName = _configType.getClassName();

    TypeSpec implClass = generateImplClass(configName);
    generateConstructClass(configName, implClass);
  }

  private TypeSpec generateImplClass(String configName) throws IOException {
    TypeSpec implClass = TypeSpec.classBuilder(configName + "Impl")
        .addModifiers(Modifier.FINAL)
        .addSuperinterface(_configType.toTypeName())
        .addMethods(makeOverrideList())
        .addFields(makeFields())
        .build();

    _configType.saveInSamePackage(implClass);
    return implClass;
  }

  private void generateConstructClass(String configName, TypeSpec implClass) throws IOException {
    TypeSpec constructClass = TypeSpec.classBuilder(configName + "Construct")
        .addModifiers(Modifier.FINAL)
        .addAnnotation(Component.class)
        .addSuperinterface(getConstructorInterface())
        .addMethod(makeConstruct(implClass))
        .build();

    _configType.saveInSamePackage(constructClass);
  }

  private List<MethodSpec> makeOverrideList() {
    return _configType.getMethodList().stream()
        .map(method -> method.override()
            .addStatement("return $L", method.getName())
            .build())
        .collect(Collectors.toList());
  }

  private List<FieldSpec> makeFields() {
    return _configType.getMethodList().stream()
        .map(m -> FieldSpec.builder(m.getReturnType(), m.getName()).build())
        .collect(Collectors.toList());
  }

  private TypeName getConstructorInterface() {
    ClassName constructorType = ClassName.get(ConfigConstructor.class);
    return ParameterizedTypeName.get(constructorType, _configType.toTypeName());
  }

  private MethodSpec makeConstruct(TypeSpec implClass) {
    return MethodSpec.methodBuilder("construct")
        .returns(_configType.toTypeName())
        .addModifiers(Modifier.PUBLIC)
        .addAnnotation(Override.class)
        .addStatement("return new $L()", implClass.name)
        .build();
  }

  interface ConfigType {

    String getClassName();

    TypeName toTypeName();

    List<Method> getMethodList();

    void saveInSamePackage(TypeSpec type) throws IOException;
  }

  interface Method {

    String getName();

    TypeName getReturnType();

    MethodSpec.Builder override();
  }

  private final ConfigType _configType;
}
