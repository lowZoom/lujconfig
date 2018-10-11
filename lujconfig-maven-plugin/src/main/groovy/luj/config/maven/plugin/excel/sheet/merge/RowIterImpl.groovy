package luj.config.maven.plugin.excel.sheet.merge

import groovy.transform.PackageScope
import luj.groovy.AutoCtor
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Workbook

@PackageScope
@AutoCtor
class RowIterImpl implements Iterator<TableRowMergerImpl.Row> {

  @Override
  boolean hasNext() {
    return _iter.hasNext()
  }

  @Override
  TableRowMergerImpl.Row next() {
    Row poiRow = _iter.next()
    Workbook workbook = poiRow.getSheet().getWorkbook()

    def eval = workbook.getCreationHelper().createFormulaEvaluator()
    return new RowImpl(poiRow, eval)
  }

  private Iterator<Row> _iter
}
