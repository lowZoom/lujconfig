package luj.config.anno.proc.meta

import spock.lang.Specification

class ConfigMetaGeneratorImplTest extends Specification {

  String _className
  String _output

  void setup() {
    // NOOP
  }

  def "Generate:"() {
    given:
    _className = 'TestCfg'

    when:
    generate()

    then:
    _output.contains('final class TestCfgMeta')
  }

  void generate() {
    new ConfigMetaGeneratorImpl(mockDeclaration()).generate()
  }

  def mockDeclaration() {
    return [
        getClassName: { _className },
        writeToFile : { _output = it.toString() },
    ] as ConfigMetaGeneratorImpl.ConfigDeclaration
  }
}
