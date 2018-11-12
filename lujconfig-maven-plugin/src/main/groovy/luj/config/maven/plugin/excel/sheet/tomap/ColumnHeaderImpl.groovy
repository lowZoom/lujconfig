package luj.config.maven.plugin.excel.sheet.tomap

import groovy.transform.PackageScope

@PackageScope
class ColumnHeaderImpl implements SheetToMapConverterImpl.ColumnHeader {

  ColumnHeaderImpl(String headerStr) {
    _headerStr = headerStr
  }

  @Override
  String getName() {
    return _headerStr.replaceAll(/\[]/, '').trim()
  }

  @Override
  boolean isList() {
    return _headerStr.contains('[]')
  }

  private final String _headerStr
}
