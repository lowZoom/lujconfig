package luj.config.maven.plugin.excel.sheet.merge.row.cell

import com.google.common.math.DoubleMath
import groovy.transform.PackageScope
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.CellValue

@PackageScope
class CellValueGetterImpl implements CellValueGetter {

  CellValueGetterImpl(CellValue cellValue) {
    _cellValue = cellValue
  }

  @Override
  Object getValue() {
    if (_cellValue == null) {
      return null
    }

    CellType cellType = _cellValue.getCellTypeEnum()
    if (cellType == CellType.STRING) {
      return _cellValue.getStringValue()
    }

    if (cellType == CellType.NUMERIC) {
      double val = _cellValue.getNumberValue()
      if (DoubleMath.isMathematicalInteger(val)) {
        return (int) val
      }
      return val
    }

    assert false
  }

  private final CellValue _cellValue
}
