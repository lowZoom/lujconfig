package luj.config.internal.cache.value

import spock.lang.Specification

class ConfigValueMapLoaderImplTest extends Specification {

  List _fileList
  List _output

  void setup() {
    _output = []
  }

  def "Load:"() {
    given:
    _fileList = [
        [TestConfig1, [[id: '101'], [id: '102']]],
        [TestConfig2, null], // 文件不存在
    ]

    when:
    def result = load()

    then:
    ConfigValueMapLoader.Value value = result[TestConfig1]['101']
    value.configInstance == [id: '101']

    value.getLinkableList().isEmpty()
    value.getParentMap().is(result)

    _output == ['TestConfig2.logAbsent']
  }

  Map load() {
    return new ConfigValueMapLoaderImpl(_fileList.collect { mockFile(it) }).load()
  }

  def mockFile(List value) {
    def stub = Stub(ConfigValueMapLoaderImpl.ConfigFile)
    def configType = value[0] as Class

    stub.getConfigType() >> { configType }
    stub.isAbsent() >> { !value[1] }
    stub.logAbsent() >> { _output << "${configType.simpleName}.logAbsent" }
    stub.readLines() >> { value[1].collect { mockLine(it) } }

    return stub
  }

  def mockLine(Map value) {
    def stub = Stub(ConfigValueMapLoaderImpl.ConfigLine)
    stub.getId() >> { value['id'] }
    stub.getInstance() >> { value }
    return stub
  }

  private interface TestConfig1 {
    // NOOP
  }

  private interface TestConfig2 {
    // NOOP
  }
}
