package luj.config.internal.json.parse

import spock.lang.Specification

class LineJsonParserImplTest extends Specification {

  String _jsonStr

  void setup() {
    // NOOP
  }

  def "Parse:没关联id"() {
    given:
    _jsonStr = /{"id":101, "name":"名"}/

    when:
    def result = parse()

    then:
    def instance = result.configInstance as TestCfg
    instance.id() == '101'
    instance.name() == '名'
    instance.list().isEmpty()

    result.linkableList.isEmpty()
  }

  def "Parse:有关联id"() {
    given:
    _jsonStr = /{"list":[101,102]}/

    when:
    def result = parse()

    then:
    result.configInstance instanceof TestCfg
    linkable(result) == [
        ['list', ['101', '102']],
    ]
  }

  LineJsonParser.Result parse() {
    return new LineJsonParserImpl(_jsonStr, mockConfig()).parse()
  }

  def mockConfig() {
    return [
        getConfigInterface: { TestCfg },
        createInstance    : { new TestCfgImpl() },
    ] as LineJsonParserImpl.Config
  }

  def linkable(LineJsonParser.Result result) {
    return result.linkableList.collect { [it.field.name, it.idList] }
  }

  private interface TestCfg {

    String id()

    String name()

    List<TestCfg> list()
  }

  private class TestCfgImpl implements TestCfg {

    @Override
    String id() {
      return id
    }

    @Override
    String name() {
      return name
    }

    @Override
    List<TestCfg> list() {
      return list
    }

    private String id
    private String name
    private List<TestCfg> list
  }
}
