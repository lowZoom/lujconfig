package luj.config.maven.plugin.excel.sheet

import spock.lang.Specification

class SheetToMapConverterImplTest extends Specification {

  List _header
  List _dataList

  void setup() {
    // NOOP
  }

  def 'ToMaps'() {
    given:
    _header = ['a', 'b', 'c']

    _dataList = [
        [101, 'a', [3, 4, 5]],
        [102, 'bb', []],
    ]

    when:
    def result = toMaps()

    then:
    result == [
        [a: 101, b: 'a', c: [3, 4, 5]],
        [a: 102, b: 'bb', c: []],
    ]
  }

  List toMaps() {
    return new SheetToMapConverterImpl(mockSheet()).toMaps()
  }

  def mockSheet() {
    return [
        getHeader: { mockHeader() },
    ] as SheetToMapConverterImpl.Sheet
  }

  def mockHeader() {
    return [
        getColumnCount: { _header.size() },
        getColumn     : { _header[it] },
        getDataList   : { _dataList.collect { mockData(it) } },
    ] as SheetToMapConverterImpl.Header
  }

  def mockData(List list) {
    return [
        getColumn: { list[it] },
    ] as SheetToMapConverterImpl.Data
  }
}
