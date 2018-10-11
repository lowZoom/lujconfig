package luj.config.maven.plugin.excel.sheet.merge.cell

import org.apache.poi.ss.usermodel.CellValue

interface CellValueGetter {

  abstract class Factory {

    static CellValueGetter create(CellValue cell) {
      return new CellValueGetterImpl(cell)
    }
  }

  Object getValue()
}
