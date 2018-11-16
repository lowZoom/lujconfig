package luj.config.maven.plugin.excel.sheet.tomap.merge.column

import spock.lang.Specification

class SheetColumnMergerImplTest extends Specification {

  List _columnList

  void setup() {
    // NOOP
  }

  def "Merge:有组"() {
    given:
    _columnList = [
        [name: 'id'],
        [groupName: 'act', name: 'id'],
        [group: true, name: 'prob'],
    ]

    when:
    def result = merge()

    then:
    result.collect { field(it) } == [
        ['id', 0],
        ['act', ['id', 1], ['prob', 2]],
    ]
  }

  def "Merge:连续非组"() {
    given:
    _columnList = [
        [name: 'col0'],
        [name: 'col1'],
    ]

    when:
    def result = merge()

    then:
    result.collect { field(it) } == [
        ['col0', 0],
        ['col1', 1],
    ]
  }

  List<SheetColumnMerger.Field> merge() {
    return new SheetColumnMergerImpl(mockColumnIter()).merge()
  }

  def mockColumnIter() {
    def mockColumn = { Map col, int index ->
      def stub = Stub(SheetColumnMergerImpl.ExcelColumn)
      stub.getGroupName() >> { col['groupName'] }
      stub.hasGroup() >> { col['group'] as boolean }
      stub.getFieldName() >> { col['name'] }
      stub.getColumnIndex() >> { index }
      stub.toString() >> { col.toString() }
      return stub
    }
    return (0..<_columnList.size())
        .collect { mockColumn(_columnList[it], it) }
        .iterator()
  }

  def field(SheetColumnMerger.Field f) {
    if (!f.getChildren()) {
      return [f.getName(), f.getExcelColumnIndex()]
    }
    return [f.getName(), *f.getChildren().collect { field(it) }]
  }
}
