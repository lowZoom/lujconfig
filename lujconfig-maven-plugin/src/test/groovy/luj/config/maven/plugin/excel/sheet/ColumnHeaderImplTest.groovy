package luj.config.maven.plugin.excel.sheet

import spock.lang.Specification

class ColumnHeaderImplTest extends Specification {

  String _headerStr

  void setup() {
    // NOOP
  }

  def "GetName"() {
    given:
    _headerStr = 'name[] '

    when:
    def result = sut().getName()

    then:
    result == 'name'
  }

  def "IsList"() {
    given:
    _headerStr = 'name[]'

    when:
    def result = sut().isList()

    then:
    result
  }

  ColumnHeaderImpl sut() {
    return new ColumnHeaderImpl(_headerStr)
  }
}
