package luj.config.ex.internal.generate.extract.row;

import static com.google.common.base.Preconditions.checkState;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

final class FieldValueReader {

  FieldValueReader(Cell poiCell, Class<?> dataType) {
    _poiCell = poiCell;
    _dataType = dataType;
  }

  public Object read() {
    if (_poiCell.getCellTypeEnum() == CellType.BLANK) {
      return null;
    }

    CellType actualType = getCellType();
    if (actualType == CellType.STRING && _poiCell.getStringCellValue().isEmpty()) {
      return null;
    }

    if (_dataType == int.class) {
      return getIntValue(actualType);
    }

    if (_dataType == double.class) {
      return getDoubleValue(actualType);
    }

    if (_dataType == boolean.class) {
      return Boolean.TRUE; //getIntValue(actualType) > 0;
    }

    return getStringValue(actualType);
  }

  private int getIntValue(CellType cellType) {
    return (int) Math.round(getDoubleValue(cellType));
  }

  private double getDoubleValue(CellType cellType) {
    if (cellType == CellType.STRING) {
      String stringVal = _poiCell.getStringCellValue();
      checkState(!stringVal.isEmpty());
      return Double.parseDouble(stringVal);
    }
    return _poiCell.getNumericCellValue();
  }

  private String getStringValue(CellType cellType) {
    if (cellType == CellType.NUMERIC) {
      return Double.toString(_poiCell.getNumericCellValue());
    }
    return _poiCell.getStringCellValue();
  }

  private CellType getCellType() {
    CellType cellType = _poiCell.getCellTypeEnum();
    if (cellType == CellType.FORMULA) {
      return _poiCell.getCachedFormulaResultTypeEnum();
    }
    return cellType;
  }

  private final Cell _poiCell;

  private final Class<?> _dataType;
}
