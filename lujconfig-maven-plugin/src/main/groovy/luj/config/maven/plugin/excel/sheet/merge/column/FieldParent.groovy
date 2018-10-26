package luj.config.maven.plugin.excel.sheet.merge.column

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class FieldParent implements SheetColumnMerger.Field {

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

  private List<SheetColumnMergerImpl.ExcelColumn> _group
}
