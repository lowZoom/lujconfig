package luj.config.maven.plugin.excel.sheet.merge.cell

import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import spock.lang.Shared
import spock.lang.Specification

class CellValueGetterImplTest extends Specification {

  @Shared
  Workbook _workbook

  int _row

  void setupSpec() {
    _workbook = new XSSFWorkbook({ Class c ->
      c.getResourceAsStream("${c.simpleName}.xlsx")
    }(CellValueGetterImplTest.class))
  }

  def 'GetValue:真.空'() {
    given:
    _row = 0

    when:
    def result = getValue()

    then:
    result == null
  }

  def 'GetValue:空格字符'() {
    given:
    _row = 1

    when:
    def result = getValue()

    then:
    result == ' '
  }

  def 'GetValue:空字符串'() {
    given:
    _row = 2

    when:
    def result = getValue()

    then:
    result == ''
  }

  def 'GetValue:整数'() {
    given:
    _row = 3

    when:
    def result = getValue()

    then:
    result instanceof Integer
    result == 1
  }

  def 'GetValue:浮点数'() {
    given:
    _row = 4

    when:
    def result = getValue()

    then:
    result == 1.99999
  }

  Object getValue() {
    def eval = _workbook.getCreationHelper().createFormulaEvaluator()
    def cell = _workbook.getSheetAt(0).getRow(_row).getCell(1)
    return new CellValueGetterImpl(eval.evaluate(cell)).getValue()
  }
}
