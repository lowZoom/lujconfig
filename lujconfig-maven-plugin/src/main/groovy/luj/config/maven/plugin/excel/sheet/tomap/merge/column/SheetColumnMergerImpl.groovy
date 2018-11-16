package luj.config.maven.plugin.excel.sheet.tomap.merge.column

import groovy.transform.PackageScope

@PackageScope
class SheetColumnMergerImpl implements SheetColumnMerger {

  SheetColumnMergerImpl(Iterator<ExcelColumn> columnIter) {
    _columnIter = columnIter
  }

  @Override
  List<Field> merge() {
    List groupList = []

    List<ExcelColumn> lastGroup = [_columnIter.next()]
    while (_columnIter.hasNext()) {
      List<ExcelColumn> nextGroup = collectGroup(lastGroup)

      groupList.add(lastGroup)
      lastGroup = nextGroup
    }
    if (lastGroup) {
      groupList.add(lastGroup)
    }

    return groupList.collect { createField(it) }
  }

  private List collectGroup(List<ExcelColumn> curGroup) {
    while (_columnIter.hasNext()) {
      ExcelColumn column = _columnIter.next()
      if (!column.hasGroup() || column.getGroupName()) {
        return [column]
      }
      curGroup.add(column)
    }
    return []
  }

  private Field createField(List<ExcelColumn> group) {
    if (group.size() == 1) {
      ExcelColumn column = group[0]
      if (!column.hasGroup()) {
        return new FieldLeaf(column)
      }
    }
    return new FieldParent(group)
  }

  interface ExcelColumn {

    String getGroupName()

    boolean hasGroup()

    String getFieldName()

    int getColumnIndex()
  }

  private final Iterator<ExcelColumn> _columnIter
}
