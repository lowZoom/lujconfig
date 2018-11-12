package luj.config.maven.plugin.excel.sheet.tomap.json

import groovy.transform.PackageScope
import luj.config.maven.plugin.excel.sheet.tomap.merge.column.SheetColumnMerger

@PackageScope
class HeaderImpl implements JsonValueMakerImpl.Header {

  HeaderImpl(List<SheetColumnMerger.Field> fieldList) {
    _fieldList = fieldList
  }

  @Override
  boolean isObjectType() {
    return _fieldList
  }

  @Override
  int getFieldCount() {
    return _fieldList.size()
  }

  @Override
  String getFieldName(int index) {
    return _fieldList[index].getName()
  }

  private final List<SheetColumnMerger.Field> _fieldList
}
