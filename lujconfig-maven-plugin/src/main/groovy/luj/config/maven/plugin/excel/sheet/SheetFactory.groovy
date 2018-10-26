package luj.config.maven.plugin.excel.sheet

import groovy.transform.PackageScope
import luj.config.maven.plugin.excel.sheet.merge.column.SheetColumnMerger
import luj.groovy.AutoCtor
import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet

@PackageScope
@AutoCtor
class SheetFactory {

  SheetImpl create() {
    def rowIter = _sheet.rowIterator()
    assert rowIter.hasNext()

    Row firstRow = rowIter.next()
    skipHeader(rowIter)

    List<String> headerList = (0..<firstRow.lastCellNum)
        .collect { firstRow.getCell(it) }
        .collect { it.toString() }

    List<SheetColumnMerger.Field> fieldList = SheetColumnMerger.Factory.create(headerList).merge()
    return new SheetImpl(fieldList, rowIter)
  }

  private void skipHeader(Iterator<Row> rowIter) {
    while (rowIter.hasNext()) {
      def row = rowIter.next()
      if (!row.formatted) {
        continue
      }

      if (row.rowStyle.borderBottomEnum != BorderStyle.NONE) {
        // 以下框线为表头边界
        return
      }
    }
  }

  private Sheet _sheet
}
