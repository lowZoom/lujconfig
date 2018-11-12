package luj.config.maven.plugin.excel.sheet.tomap

import groovy.transform.PackageScope
import luj.config.maven.plugin.excel.sheet.tomap.merge.column.SheetColumnMerger
import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet

@PackageScope
class SheetFactory {

  SheetFactory(Sheet sheet) {
    _sheet = sheet
  }

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

  private final Sheet _sheet
}
