package luj.config.maven.plugin.excel.sheet.tomap.merge.row

import spock.lang.Specification

class TableRowMergerImplTest extends Specification {

  List _rows

  void setup() {
    // NOOP
  }

  def 'Merge:多行'() {
    given:
    _rows = [
        [1, 'a', 'a1'],
        [null, '', 'a2'],
        ['', ' ', 'a3'],
        [2, 'b'],
        [3, 'c', 'c1'],
        [4],
    ]

    when:
    def result = merge()

    then:
    result == [
        [[1], ['a'], ['a1', 'a2', 'a3']],
        [[2], ['b'], []],
        [[3], ['c'], ['c1']],
        [[4], [], []],
    ]
  }

  def 'Merge:列数与行数不同'() {
    given:
    _rows = [
        ['1', 'a', 'a1'],
        [null, 'a', ''],
    ]

    when:
    def result = merge()

    then:
    result == [
        [['1'], ['a', 'a'], ['a1']],
    ]
  }

  def 'Merge:内嵌对象'() {
    given:
    _rows = [
        ['m106', ['s201', 50]],
        [null, ['s205', 30]],
    ]

    when:
    def result = merge()

    then:
    result == [
        [['m106'], [['s201', 50], ['s205', 30]]],
    ]
  }

  List merge() {
    return new TableRowMergerImpl(mockRowIter()).merge()
  }

  def mockRowIter() {
    return _rows
        .collect { mockRow(it) }
        .iterator()
  }

  def mockRow(List value) {
    return [
        getColumn  : { value[it] },
        countColumn: { -> value.size() },
    ] as TableRowMergerImpl.Row
  }
}
