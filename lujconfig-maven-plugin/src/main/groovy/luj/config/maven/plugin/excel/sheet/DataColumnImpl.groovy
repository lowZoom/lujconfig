package luj.config.maven.plugin.excel.sheet

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class DataColumnImpl implements SheetToMapConverterImpl.DataColumn {

  @Override
  SheetToMapConverterImpl.ColumnHeader getHeader() {
    return _header
  }

  @Override
  List getValue() {
    return _value
  }

  private ColumnHeaderImpl _header

  private List _value
}
