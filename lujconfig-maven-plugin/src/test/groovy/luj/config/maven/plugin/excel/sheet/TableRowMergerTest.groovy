package luj.config.maven.plugin.excel.sheet

import spock.lang.Specification

class TableRowMergerTest extends Specification {

  Iterator<TableRowMerger.Row> _rowIter

  void setup() {
    // NOOP
  }

  def 'merge:多行'() {
    given:
    _rowIter = mockRows([
        ['1', 'a', 'a1'],
        [null, '', 'a2'],
        ['', ' ', 'a3'],
        ['2', 'b'],
    ])

    when:
    def result = merge()

    then:
    result == [
        [['1'], ['a'], ['a1', 'a2', 'a3']],
        [['2'], ['b'], []],
    ]
  }

  def 'merge:列数与行数不同'() {
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
    return new TableRowMerger(_rowIter).merge()
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
    ] as TableRowMerger.Row
  }
}
