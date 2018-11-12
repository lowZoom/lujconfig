package luj.config.maven.plugin.excel.sheet.tomap.json

import spock.lang.Specification

class JsonValueMakerImplTest extends Specification {

  List _header
  List _value

  void setup() {
    // NOOP
  }

  def "Make:简单类型"() {
    given:
    _header = null
    _value = [123]

    when:
    def result = make()

    then:
    result == [123]
  }

  def "Make:对象类型"() {
    given:
    _header = ['a', 'b']
    _value = [
        [11, 12],
        [21, 22],
    ]

    when:
    def result = make()

    then:
    result == [
        [a: 11, b: 12],
        [a: 21, b: 22],
    ]
  }

  List make() {
    return new JsonValueMakerImpl(mockHeader(), _value).make()
  }

  def mockHeader() {
    return [
        isObjectType : { _header as boolean },
        getFieldCount: { _header.size() },
        getFieldName : { _header[it] },
    ] as JsonValueMakerImpl.Header
  }
}
