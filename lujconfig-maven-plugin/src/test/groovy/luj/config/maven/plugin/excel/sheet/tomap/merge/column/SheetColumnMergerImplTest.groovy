package luj.config.maven.plugin.excel.sheet.tomap.merge.column

import spock.lang.Specification

class SheetColumnMergerImplTest extends Specification {

  List _columnList

  void setup() {
    // NOOP
  }

  def 'Merge:'() {
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

  List<SheetColumnMerger.Field> merge() {
    return new SheetColumnMergerImpl(mockColumnIter()).merge()
  }

  def mockColumnIter() {
    def mockColumn = { Map col, int index ->
      return [
          getGroupName  : { col['groupName'] },
          hasGroup      : { col['group'] as boolean },
          getFieldName  : { col['name'] },
          getColumnIndex: { index },
          toString      : { col.toString() },
      ] as SheetColumnMergerImpl.ExcelColumn
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
