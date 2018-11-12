package luj.config.maven.plugin.excel.sheet.tomap

import spock.lang.Specification

class SheetToMapConverterImplTest extends Specification {

  List<Map> _header
  List _rowList

  void setup() {
    // NOOP
  }

  def 'ToMaps:非数组'() {
    given:
    _header = [
        [name: 'a'],
        [name: 'b'],
        [name: 'c'],
    ]

    _rowList = [
        [[101], ['AA'], []],
        [[102], [], []],
    ]

    when:
    def result = toMaps()

    then:
    result == [
        [a: 101, b: 'AA'],
        [a: 102],
    ]
  }

  def 'ToMaps:数组'() {
    given:
    _header = [
        [name: 'a', list: true],
        [name: 'b', list: true],
        [name: 'c', list: true],
    ]

    _rowList = [
        [[101, 102], ['AA'], []],
        [[201], [], []],
    ]

    when:
    def result = toMaps()

    then:
    result == [
        [a: [101, 102], b: ['AA']],
        [a: [201]],
    ]
  }

  List toMaps() {
    return new SheetToMapConverterImpl(mockSheet()).toMaps()
  }

  def mockSheet() {
    return [
        getRowList    : { _rowList.collect { mockRow(it) } },
        getFieldCount: { _header.size() },
    ] as SheetToMapConverterImpl.Sheet
  }

  def mockRow(List row) {
    return [
        getColumn: { mockColumn(row, it) },
    ] as SheetToMapConverterImpl.DataRow
  }

  def mockColumn(List row, int col) {
    Map headerMap = _header[col]
    def header = [
        getName: { headerMap['name'] },
        isList : { headerMap['list'] as boolean },
    ] as SheetToMapConverterImpl.ColumnHeader

    return [
        getHeader: { header },
        getValue : { row[col] },
    ] as SheetToMapConverterImpl.DataColumn
  }
}
