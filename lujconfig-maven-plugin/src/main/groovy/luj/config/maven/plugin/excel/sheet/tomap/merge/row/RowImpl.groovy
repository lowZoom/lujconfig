package luj.config.maven.plugin.excel.sheet.tomap.merge.row

import groovy.transform.PackageScope
import luj.config.maven.plugin.excel.sheet.tomap.merge.column.SheetColumnMerger
import luj.config.maven.plugin.excel.sheet.tomap.merge.row.cell.CellValueGetter
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.FormulaEvaluator
import org.apache.poi.ss.usermodel.Row

/**
 * 对应excel里的一行
 */
@PackageScope
class RowImpl implements TableRowMergerImpl.Row {
  RowImpl(List<SheetColumnMerger.Field> headerList, Row row, FormulaEvaluator formulaEval) {
    _headerList = headerList

    _row = row
    _formulaEval = formulaEval
  }

  /**
   * 取出聚合后的一列，一列可能就代表一个复合对象
   */
  @Override
  Object getColumn(int index) {
    SheetColumnMerger.Field field = _headerList[index]
    List<SheetColumnMerger.Field> subFields = field.getChildren()
    if (!subFields) {
      return getCellValue(field.getExcelColumnIndex())
    }

    return subFields
        .collect { it.getExcelColumnIndex() }
        .collect { getCellValue(it) }
  }

  @Override
  int countColumn() {
    return _headerList.size()
//    return _row.getLastCellNum()
  }

  private Object getCellValue(int col) {
    Cell cell = _row.getCell(col)
    return CellValueGetter.Factory
        .create(_formulaEval.evaluate(cell))
        .getValue()
  }

  private final List<SheetColumnMerger.Field> _headerList

  private final Row _row
  private final FormulaEvaluator _formulaEval
}
