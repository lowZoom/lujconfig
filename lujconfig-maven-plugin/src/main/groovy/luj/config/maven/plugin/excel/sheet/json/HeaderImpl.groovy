package luj.config.maven.plugin.excel.sheet.json

import groovy.transform.PackageScope
import luj.config.maven.plugin.excel.sheet.merge.column.SheetColumnMerger
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class HeaderImpl implements JsonValueMakerImpl.Header {

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

  private List<SheetColumnMerger.Field> _fieldList
}
