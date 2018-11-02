package luj.config.maven.plugin.excel.sheet.merge.row

import groovy.transform.PackageScope
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook

@PackageScope
class RowIterImpl implements Iterator<TableRowMergerImpl.Row> {

  RowIterImpl(Iterator<Row> iter, List headerList) {
    _iter = iter
    _headerList = headerList
  }

  @Override
  boolean hasNext() {
    return _iter.hasNext()
  }

  @Override
  TableRowMergerImpl.Row next() {
    Row poiRow = _iter.next()

    Workbook workbook = poiRow.getSheet().getWorkbook()
    def eval = workbook.getCreationHelper().createFormulaEvaluator()

    return new RowImpl(_headerList, poiRow, eval)
  }

  private final Iterator<Row> _iter

  private final List _headerList
}
