package luj.config.maven.plugin.excel

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.PackageScope
import luj.config.maven.plugin.excel.sheet.SheetToMapConverter
import luj.groovy.AutoCtor
import org.apache.poi.ss.usermodel.Sheet

@PackageScope
@AutoCtor
class SheetImpl implements ExcelReader.Sheet {

  @Override
  void writeJsonFile() {
    println('*' * 20)
    println(_sheet.sheetName)

    def converter = SheetToMapConverter.Factory.create(_sheet)
    List<Map> jsonList = converter.toMaps()

    String jsonStr = new ObjectMapper().writeValueAsString(jsonList)
    println(jsonStr.replace('},', '},\n'))
  }

  private Sheet _sheet
}
