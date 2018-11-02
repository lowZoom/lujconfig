package luj.config.maven.plugin.excel.sheet.merge.column

import groovy.transform.PackageScope

@PackageScope
class FieldLeaf implements SheetColumnMerger.Field {

  FieldLeaf(SheetColumnMergerImpl.ExcelColumn column) {
    _column = column
  }

  @Override
  String getName() {
    return _column.getFieldName()
  }

  @Override
  int getExcelColumnIndex() {
    return _column.getColumnIndex()
  }

  @Override
  List<SheetColumnMerger.Field> getChildren() {
    return []
  }

  @Override
  String toString() {
    return "${getName()}:${getExcelColumnIndex()}"
  }

  private final SheetColumnMergerImpl.ExcelColumn _column
}
