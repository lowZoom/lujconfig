package luj.config.maven.plugin.excel.sheet

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class DataRowImpl implements SheetToMapConverterImpl.DataRow {

  @Override
  SheetToMapConverterImpl.DataColumn getColumn(int index) {
    String headerStr = _headerList[index]
    def header = new ColumnHeaderImpl(headerStr)

    return new DataColumnImpl(header, _columnList[index])
  }

  private List<String> _headerList

  private List _columnList
}
