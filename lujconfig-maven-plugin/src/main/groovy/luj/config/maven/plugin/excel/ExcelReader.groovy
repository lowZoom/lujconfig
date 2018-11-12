package luj.config.maven.plugin.excel

import groovy.transform.PackageScope
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import java.nio.file.Path

@PackageScope
class ExcelReader {

  ExcelReader(Path excelPath, Path outputPath) {
    _excelPath = excelPath
    _outputPath = outputPath
  }

  List<Sheet> read() {
    return new XSSFWorkbook(_excelPath.toString()).sheetIterator()
        .collect { new SheetImpl(it, _outputPath) }
  }

  interface Sheet {

    void writeJsonFile()
  }

  private final Path _excelPath

  private final Path _outputPath
}
