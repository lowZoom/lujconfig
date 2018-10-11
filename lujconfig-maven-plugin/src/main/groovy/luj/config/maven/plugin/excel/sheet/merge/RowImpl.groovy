package luj.config.maven.plugin.excel.sheet.merge

import groovy.transform.PackageScope
import luj.config.maven.plugin.excel.sheet.merge.cell.CellValueGetter
import luj.groovy.AutoCtor
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.FormulaEvaluator
import org.apache.poi.ss.usermodel.Row

@PackageScope
@AutoCtor
class RowImpl implements TableRowMergerImpl.Row {

  @Override
  Object getColumn(int index) {
    Cell cell = _row.getCell(index)

    return CellValueGetter.Factory
        .create(_formulaEval.evaluate(cell))
        .getValue()
  }

  @Override
  int countColumn() {
    return _row.getLastCellNum()
  }

  private Row _row

  private FormulaEvaluator _formulaEval
}
