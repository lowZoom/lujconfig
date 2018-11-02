package luj.config.maven.plugin.excel.sheet

import groovy.transform.PackageScope
import luj.config.maven.plugin.excel.sheet.merge.column.SheetColumnMerger

@PackageScope
class DataRowImpl implements SheetToMapConverterImpl.DataRow {

  DataRowImpl(List<SheetColumnMerger.Field> headerList, List columnList) {
    _headerList = headerList
    _columnList = columnList
  }

  @Override
  SheetToMapConverterImpl.DataColumn getColumn(int index) {
    SheetColumnMerger.Field field = _headerList[index]
    def header = new ColumnHeaderImpl(field.getName())

    return new DataColumnImpl(header, field, _columnList[index])
  }

  private final List<SheetColumnMerger.Field> _headerList

  private final List _columnList
}
