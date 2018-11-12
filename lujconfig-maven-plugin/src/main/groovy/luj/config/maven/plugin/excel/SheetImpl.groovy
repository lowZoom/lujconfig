package luj.config.maven.plugin.excel

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.PackageScope
import luj.config.maven.plugin.excel.sheet.tomap.SheetToMapConverter
import org.apache.poi.ss.usermodel.Sheet

import java.nio.file.Files
import java.nio.file.Path

@PackageScope
class SheetImpl implements ExcelReader.Sheet {

  SheetImpl(Sheet sheet, Path outputPath) {
    _sheet = sheet
    _outputPath = outputPath
  }

  @Override
  void writeJsonFile() {
    println('*' * 20)
    println(_sheet.sheetName)

    def converter = SheetToMapConverter.Factory.create(_sheet)
    List<Map> jsonList = converter.toMaps()

    def jsonEncoder = new ObjectMapper()
    String fileStr = [
        '{"header":',
        '{}',
        ',"body":[',
        jsonList.collect { jsonEncoder.writeValueAsString(it) }.join(',\n'),
        ']}\n',
    ].join('\n')

    Files.write(getJsonPath(_sheet.sheetName), fileStr.bytes)
  }

  private Path getJsonPath(String fileName) {
    return _outputPath.resolve("${fileName}.json")
  }

  private final Sheet _sheet

  private final Path _outputPath
}
