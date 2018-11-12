package luj.config.maven.plugin.excel.sheet.tomap

import groovy.transform.PackageScope
import luj.config.maven.plugin.excel.sheet.tomap.json.JsonValueMaker
import luj.config.maven.plugin.excel.sheet.tomap.merge.column.SheetColumnMerger

@PackageScope
class DataColumnImpl implements SheetToMapConverterImpl.DataColumn {

  DataColumnImpl(ColumnHeaderImpl header, SheetColumnMerger.Field field, List value) {
    _header = header
    _field = field

    _value = value
  }

  @Override
  SheetToMapConverterImpl.ColumnHeader getHeader() {
    return _header
  }

  @Override
  List getValue() {
    return JsonValueMaker.Factory.create(_field, _value).make()
  }

  private final ColumnHeaderImpl _header
  private final SheetColumnMerger.Field _field

  private final List _value
}
