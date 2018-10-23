package luj.config.maven.plugin.excel.sheet

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class ColumnHeaderImpl implements SheetToMapConverterImpl.ColumnHeader {

  @Override
  String getName() {
    return _headerStr.replaceAll(/\[]/, '').trim()
  }

  @Override
  boolean isList() {
    return _headerStr.contains('[]')
  }

  private String _headerStr
}
