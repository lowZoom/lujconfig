package luj.config.maven.plugin.excel.sheet.merge

import spock.lang.Specification

class TableRowMergerImplTest extends Specification {

  Iterator<TableRowMergerImpl.Row> _rowIter

  void setup() {
    // NOOP
  }

  def 'Merge:多行'() {
    given:
    _rowIter = mockRows([
        ['1', 'a', 'a1'],
        [null, '', 'a2'],
        ['', ' ', 'a3'],
        ['2', 'b'],
        ['3', 'c', 'c1'],
        ['4'],
    ])

    when:
    def result = merge()

    then:
    result == [
        [['1'], ['a'], ['a1', 'a2', 'a3']],
        [['2'], ['b'], []],
        [['3'], ['c'], ['c1']],
        [['4'], [], []],
    ]
  }

  def 'Merge:列数与行数不同'() {
    given:
    _rowIter = mockRows([
        ['1', 'a', 'a1'],
        [null, 'a', ''],
    ])

    when:
    def result = merge()

    then:
    result == [
        [['1'], ['a', 'a'], ['a1']],
    ]
  }

  List merge() {
    return new TableRowMergerImpl(_rowIter).merge()
  }

  def mockRows(List input) {
    return input
        .collect { mockRow(it) }
        .iterator()
  }

  def mockRow(List<String> value) {
    return [
        getColumn  : { value[it] },
        countColumn: { -> value.size() },
    ] as TableRowMergerImpl.Row
  }
}
