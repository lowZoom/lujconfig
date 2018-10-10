package luj.config.maven.plugin.excel.sheet

import groovy.transform.PackageScope
import luj.groovy.AutoCtor
import org.apache.poi.ss.usermodel.BorderStyle
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet

@PackageScope
@AutoCtor
class SheetImpl implements SheetToMapConverterImpl.Sheet {

  @Override
  SheetToMapConverterImpl.Header getHeader() {
    def rowIter = _sheet.rowIterator()
    assert rowIter.hasNext()

    Row firstRow = rowIter.next()
    skipHeader(rowIter)

    List<String> headerList = (0..<firstRow.lastCellNum)
        .collect { firstRow.getCell(it) }
        .collect { it.toString() }

    return new HeaderImpl(headerList, rowIter)
  }

  private void skipHeader(Iterator<Row> rowIter) {
    while (rowIter.hasNext()) {
      def row = rowIter.next()
      if (!row.formatted) {
        continue
      }

      if (row.rowStyle.borderBottomEnum != BorderStyle.NONE) {
        return
      }
    }
  }

  private Sheet _sheet
}
