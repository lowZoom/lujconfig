package luj.config.maven.plugin.excel

import groovy.transform.PackageScope
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import java.nio.file.Path

@PackageScope
class ExcelReader {

  ExcelReader(Path excelPath) {
    _excelPath = excelPath
  }

  List<Sheet> read() {
    return new XSSFWorkbook(_excelPath.toString()).sheetIterator()
        .collect { new SheetImpl(it, _excelPath) }
  }

  interface Sheet {

    void writeJsonFile()
  }

  private final Path _excelPath
}
