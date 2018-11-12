package luj.config.maven.plugin.excel.sheet.output

import groovy.transform.PackageScope

import java.nio.file.Path

@PackageScope
class SheetOutputNameResolverImpl implements SheetOutputNameResolver {

  SheetOutputNameResolverImpl(Path excelPath, String sheetName) {
    _excelPath = excelPath
    _sheetName = sheetName
  }

  @Override
  String resolve() {
    String excelFileName = _excelPath.getFileName().toString()
    int dotIndex = excelFileName.lastIndexOf('.')
    String excelName = (dotIndex < 0) ? excelFileName : excelFileName.substring(0, dotIndex)
    return "${excelName}.${_sheetName}.json"
  }

  private final Path _excelPath

  private final String _sheetName
}
