package luj.config.internal.cache

import spock.lang.Specification

class ConfigCacheLoaderImplTest extends Specification {

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
    result.get(TestConfig1, '101') == [id: '101']
    _output == ['TestConfig2.logAbsent']
  }

  ConfigCache load() {
    return new ConfigCacheLoaderImpl(_fileList.collect { mockFile(it) }).load()
  }

  def mockFile(List value) {
    def configType = value[0] as Class
    return [
        getConfigType: { configType },
        isAbsent     : { !value[1] },
        logAbsent    : { _output << "${configType.simpleName}.logAbsent" },
        readLines    : { value[1].collect { mockLine(it) } },
    ] as ConfigCacheLoaderImpl.ConfigFile
  }

  def mockLine(Map value) {
    return [
        getId   : { value['id'] },
        getValue: { value },
    ] as ConfigCacheLoaderImpl.ConfigLine
  }

  private interface TestConfig1 {
    // NOOP
  }

  private interface TestConfig2 {
    // NOOP
  }
}
