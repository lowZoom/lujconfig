package luj.config.maven.plugin.excel

import groovy.transform.PackageScope
import luj.groovy.AutoCtor
import org.apache.poi.xssf.usermodel.XSSFWorkbook

@PackageScope
@AutoCtor
class ExcelReader {

  List<Sheet> read() {
    return new XSSFWorkbook(_excelPath).sheetIterator()
        .collect { new SheetImpl(it) }
  }

  interface Sheet {

    void writeJsonFile()
  }

  private String _excelPath
}
