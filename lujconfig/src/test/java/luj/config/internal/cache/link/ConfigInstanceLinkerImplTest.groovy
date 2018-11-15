package luj.config.internal.cache.link

import spock.lang.Specification

class ConfigInstanceLinkerImplTest extends Specification {

  Map _typeMap

  void setup() {
    // NOOP
  }

  def "Link:"() {
    given:
    _typeMap = [
        (TestCfg): ['101': new TestCfg()],
    ]

    when:
    def result = link()

    then:
    TestCfg cfg = result[TestCfg]['101']
    cfg._id == '101'
  }

  Map link() {
    def typeMapMock = _typeMap.collectEntries { k, v -> [(k): mockValueMap(v)] }
    return new ConfigInstanceLinkerImpl(typeMapMock).link()
  }

  def mockValueMap(Map value) {
    return value.collectEntries { k, v ->
      [(k): ([
          getLinkableList  : { [mockField(v, k)] },
          getConfigInstance: { v },
      ] as ConfigInstanceLinkerImpl.ConfigValue)]
    }
  }

  def mockField(TestCfg cfg, String id) {
    return [
        link: { cfg._id = id },
    ] as ConfigInstanceLinkerImpl.LinkableField
  }

  private class TestCfg {

    String _id
  }
}
