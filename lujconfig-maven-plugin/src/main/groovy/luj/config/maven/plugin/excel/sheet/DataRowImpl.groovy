package luj.config.maven.plugin.excel.sheet

import groovy.transform.PackageScope
import luj.config.maven.plugin.excel.sheet.merge.column.SheetColumnMerger
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class DataRowImpl implements SheetToMapConverterImpl.DataRow {

  @Override
  SheetToMapConverterImpl.DataColumn getColumn(int index) {
    SheetColumnMerger.Field field = _headerList[index]
    def header = new ColumnHeaderImpl(field.getName())

    return new DataColumnImpl(header, field, _columnList[index])
  }

  private List<SheetColumnMerger.Field> _headerList

  private List _columnList
}
