package luj.config.maven.plugin.excel.sheet.tomap.merge.column

import groovy.transform.PackageScope

@PackageScope
class ExcelColumnImpl implements SheetColumnMergerImpl.ExcelColumn {

  ExcelColumnImpl(String headerStr, int columnIndex) {
    _headerStr = headerStr
    _columnIndex = columnIndex
  }

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

  private final String _headerStr

  private final int _columnIndex
}
