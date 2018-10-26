package luj.config.maven.plugin.excel.sheet

import groovy.transform.PackageScope
import luj.config.maven.plugin.excel.sheet.json.JsonValueMaker
import luj.config.maven.plugin.excel.sheet.merge.column.SheetColumnMerger
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
    return JsonValueMaker.Factory.create(_field, _value).make()
  }

  private ColumnHeaderImpl _header
  private SheetColumnMerger.Field _field

  private List _value
}
