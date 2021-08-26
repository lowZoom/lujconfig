package luj.config.internal.container.item.add

import spock.lang.Specification

class ValueMapAlignerTest extends Specification {

  Map _input

  void setup() {
    // NOOP
  }

  def "Align:输入全String"() {
    given:
    _input = [
        'intUnbox' : "1",
        'intBox' : "2",
    ]

    when:
    def result = align()

    then:
    result == [
      'intUnbox' : 1,
      'intBox' : 2,
    ]
  }

  Map align() {
    return ValueMapAligner.GET.align(_input, Input)
  }

  @SuppressWarnings("unused")
  interface Input {

    int intUnbox()

    Integer intBox()
  }
}
