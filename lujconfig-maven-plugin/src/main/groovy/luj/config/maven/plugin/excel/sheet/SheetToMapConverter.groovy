package luj.config.maven.plugin.excel.sheet

import org.apache.poi.ss.usermodel.Sheet

interface SheetToMapConverter {

  abstract class Factory {

    static SheetToMapConverter create(Sheet sheet) {
      return new SheetToMapConverterImpl(new SheetImpl(sheet))
    }
  }

  List<Map> toMaps()
}
