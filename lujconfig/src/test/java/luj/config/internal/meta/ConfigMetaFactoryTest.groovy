package luj.config.internal.meta

import luj.config.anno.Config
import luj.config.internal.meta.spring.ConfigMetaHolder
import spock.lang.Specification

class ConfigMetaFactoryTest extends Specification {

  ConfigMetaHolder _metaHolder

  void setup() {
    // NOOP
  }

  def "Create:"() {
    given:
    _metaHolder = new TestHolder()

    when:
    def result = create()

    then:
    result.configType == TestConfig
    result.configName == 'name'
  }

  ConfigMeta create() {
    return new ConfigMetaFactory(_metaHolder).create()
  }

  @Config('name')
  private interface TestConfig {
    // NOOP
  }

  private class TestHolder extends ConfigMetaHolder<TestConfig> {
    // NOOP
  }
}
