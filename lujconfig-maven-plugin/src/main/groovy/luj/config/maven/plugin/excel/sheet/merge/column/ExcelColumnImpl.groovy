package luj.config.maven.plugin.excel.sheet.merge.column

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class ExcelColumnImpl implements SheetColumnMergerImpl.ExcelColumn {

  @Override
  String getGroupName() {
    return _headerStr.substring(0, _headerStr.lastIndexOf('.'))
  }

  @Override
  boolean hasGroup() {
    return _headerStr.contains('.')
  }

  @Override
  String getFieldName() {
    return _headerStr.split(/\./)[-1]
  }

  @Override
  int getColumnIndex() {
    return _columnIndex
  }

  private String _headerStr

  private int _columnIndex
}
