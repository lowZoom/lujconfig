package luj.config.maven.plugin.excel.sheet.tomap

import org.apache.poi.ss.usermodel.Sheet

interface SheetToMapConverter {

  abstract class Factory {

    static SheetToMapConverter create(Sheet sheet) {
      def sheetImpl = new SheetFactory(sheet).create()
      return new SheetToMapConverterImpl(sheetImpl)
    }
  }

  List<Map> toMaps()
}
