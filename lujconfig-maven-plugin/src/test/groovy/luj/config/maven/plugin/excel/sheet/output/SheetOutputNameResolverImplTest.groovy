package luj.config.maven.plugin.excel.sheet.output

import spock.lang.Specification

import java.nio.file.Path
import java.nio.file.Paths

class SheetOutputNameResolverImplTest extends Specification {

  Path _excelPath
  String _sheetName

  void setup() {
    // NOOP
  }

  def "Resolve:"() {
    given:
    _excelPath = Paths.get('some', 'where', 'excel.xlsx')
    _sheetName = 'sheet'

    when:
    def result = resolve()

    then:
    result == 'excel.sheet.json'
  }

  String resolve() {
    return new SheetOutputNameResolverImpl(_excelPath, _sheetName).resolve()
  }
}
