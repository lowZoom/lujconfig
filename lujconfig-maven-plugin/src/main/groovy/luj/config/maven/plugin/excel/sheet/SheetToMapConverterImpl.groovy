package luj.config.maven.plugin.excel.sheet

import groovy.transform.PackageScope

@PackageScope
class SheetToMapConverterImpl implements SheetToMapConverter {

  SheetToMapConverterImpl(Sheet sheet) {
    _sheet = sheet
  }

  @Override
  List<Map> toMaps() {
    return _sheet.getRowList()
        .collect { toJsonMap(it) }
  }

  private Map toJsonMap(DataRow row) {
    return (0..<_sheet.getFieldCount())
        .collectEntries { toEntry(row.getColumn(it)) }
  }

  private Map toEntry(DataColumn field) {
    List rawValue = field.getValue()
    if (!rawValue) {
      return [:]
    }

    ColumnHeader header = field.getHeader()
    String colName = header.getName()
    if (header.isList()) {
      return [(colName): rawValue]
    }

    Object singleVal = rawValue[0]
    if (!singleVal) {
      return []
    }
    return [(colName): singleVal]
  }

  interface Sheet {

    List<DataRow> getRowList()

    int getFieldCount()
  }

  /**
   * 代表一行数据项
   */
  interface DataRow {

    DataColumn getColumn(int index)
  }

  interface DataColumn {

    ColumnHeader getHeader()

    List getValue()
  }

  interface ColumnHeader {

    String getName()

    boolean isList()
  }

  private final Sheet _sheet
}
