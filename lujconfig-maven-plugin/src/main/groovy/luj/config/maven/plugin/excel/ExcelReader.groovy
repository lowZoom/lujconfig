package luj.config.maven.plugin.excel

import groovy.transform.PackageScope
import luj.config.maven.plugin.excel.sheet.output.SheetOutputNameResolver
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
        .collect { createSheet(it) }
  }

  private SheetImpl createSheet(org.apache.poi.ss.usermodel.Sheet sheet) {
    def nameResolver = SheetOutputNameResolver.Factory.create(_excelPath, sheet)
    return new SheetImpl(sheet, _outputPath, nameResolver)
  }

  interface Sheet {

    void writeJsonFile()
  }

  private final Path _excelPath

  private final Path _outputPath
}
