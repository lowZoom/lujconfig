package luj.config.maven.plugin.excel.sheet.merge.column

import groovy.transform.PackageScope

@PackageScope
class FieldParent implements SheetColumnMerger.Field {

  FieldParent(List<SheetColumnMergerImpl.ExcelColumn> group) {
    _group = group
  }

  @Override
  String getName() {
    return _group[0].getGroupName()
  }

  @Override
  int getExcelColumnIndex() {
    assert false
  }

  @Override
  List<SheetColumnMerger.Field> getChildren() {
    return _group.collect { new FieldLeaf(it) }
  }

  @Override
  String toString() {
    return "${getName()}"
  }

  private final List<SheetColumnMergerImpl.ExcelColumn> _group
}
