package luj.config.maven.plugin.excel

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.PackageScope
import luj.config.maven.plugin.excel.sheet.SheetToMapConverter
import org.apache.poi.ss.usermodel.Sheet

@PackageScope
class SheetImpl implements ExcelReader.Sheet {

  SheetImpl(Sheet sheet) {
    _sheet = sheet
  }

  @Override
  void writeJsonFile() {
    println('*' * 20)
    println(_sheet.sheetName)

    def converter = SheetToMapConverter.Factory.create(_sheet)
    List<Map> jsonList = converter.toMaps()

    def jsonEncoder = new ObjectMapper()
    println(jsonList
        .collect { jsonEncoder.writeValueAsString(it) }
        .join('\n'))
  }

  private final Sheet _sheet
}
