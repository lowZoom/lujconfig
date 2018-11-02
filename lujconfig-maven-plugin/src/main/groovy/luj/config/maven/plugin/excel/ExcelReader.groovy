package luj.config.maven.plugin.excel

import groovy.transform.PackageScope
import org.apache.poi.xssf.usermodel.XSSFWorkbook

@PackageScope
class ExcelReader {

  ExcelReader(String excelPath) {
    _excelPath = excelPath
  }

  List<Sheet> read() {
    return new XSSFWorkbook(_excelPath).sheetIterator()
        .collect { new SheetImpl(it) }
  }

  interface Sheet {

    void writeJsonFile()
  }

  private final String _excelPath
}
