package luj.config.maven.plugin.excel.sheet

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class SheetToMapConverterImpl implements SheetToMapConverter {

  @Override
  List<Map> toMaps() {
    Header header = _sheet.header

    return header.dataList
        .collect { toJsonMap(header, it) }
  }

  private Map toJsonMap(Header header, Data data) {
    return (0..<header.columnCount)
        .collectEntries { [(header.getColumn(it)): data.getColumn(it)] }
  }

  interface Sheet {

    Header getHeader()
  }

  interface Header {

    int getColumnCount()

    String getColumn(int index)

    List<Data> getDataList()
  }

  interface Data {

    Object getColumn(int index)
  }

  private Sheet _sheet
}
