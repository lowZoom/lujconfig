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
    result.get(TestConfig1, '101').configInstance == [id: '101']
    _output == ['TestConfig2.logAbsent']
  }

  ConfigValueMapLoader.ValueMap load() {
    return new ConfigValueMapLoaderImpl(_fileList.collect { mockFile(it) }).load()
  }

  def mockFile(List value) {
    def configType = value[0] as Class
    return [
        getConfigType: { configType },
        isAbsent     : { !value[1] },
        logAbsent    : { _output << "${configType.simpleName}.logAbsent" },
        readLines    : { value[1].collect { mockLine(it) } },
    ] as ConfigValueMapLoaderImpl.ConfigFile
  }

  def mockLine(Map value) {
    return [
        getId      : { value['id'] },
        getInstance: { value },
    ] as ConfigValueMapLoaderImpl.ConfigLine
  }

  private interface TestConfig1 {
    // NOOP
  }

  private interface TestConfig2 {
    // NOOP
  }
}
