package luj.config.internal.json.parse

import spock.lang.Specification

class LineJsonParserImplTest extends Specification {

  String _jsonStr

  void setup() {
    // NOOP
  }

  def "Parse:"() {
    given:
    _jsonStr = /{"id":101, "name":"名"}/

    when:
    def result = parse()

    then:
    result.id() == '101'
    result.name() == '名'
    result.list().isEmpty()
  }

  TestCfg parse() {
    return new LineJsonParserImpl(_jsonStr, mockConfig()).parse()
  }

  def mockConfig() {
    return [
        getConfigInterface: { TestCfg },
        createInstance    : { new TestCfgImpl() },
    ] as LineJsonParserImpl.Config
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
