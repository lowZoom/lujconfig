package luj.config.maven.plugin.excel.sheet.output

import org.apache.poi.ss.usermodel.Sheet

import java.nio.file.Path

interface SheetOutputNameResolver {

  abstract class Factory {

    static SheetOutputNameResolver create(Path excelPath, Sheet sheet) {
      return new SheetOutputNameResolverImpl(excelPath, sheet.sheetName)
    }
  }

  String resolve()
}
