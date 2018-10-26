package luj.config.maven.plugin.excel.sheet.merge.column

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class FieldLeaf implements SheetColumnMerger.Field {

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
  private SheetColumnMergerImpl.ExcelColumn _column
}
