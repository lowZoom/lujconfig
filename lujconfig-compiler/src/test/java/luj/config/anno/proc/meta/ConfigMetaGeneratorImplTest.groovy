package luj.config.anno.proc.meta

import com.squareup.javapoet.ClassName
import spock.lang.Specification

class ConfigMetaGeneratorImplTest extends Specification {

  Class _configType
  String _output

  void setup() {
    // NOOP
  }

  def "Generate:"() {
    given:
    _configType = TestCfg

    when:
    generate()

    then:
    _output =~ /^final class TestCfgMeta /
    _output =~ / extends [^<]+<${ClassName.get(TestCfg)}> /
  }

  void generate() {
    new ConfigMetaGeneratorImpl(mockDeclaration()).generate()
  }

  def mockDeclaration() {
    return [
        getClassName: { _configType.simpleName },
        toTypeName  : { ClassName.get(_configType) },
        writeToFile : { _output = it.toString() },
    ] as ConfigMetaGeneratorImpl.ConfigDeclaration
  }

  interface TestCfg {
    // NOOP
  }
}
