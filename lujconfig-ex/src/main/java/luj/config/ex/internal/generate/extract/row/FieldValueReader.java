package luj.config.ex.internal.generate.extract.row;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class FieldValueReader {

  public FieldValueReader(Cell poiCell, Class<?> dataType) {
    _poiCell = poiCell;
    _dataType = dataType;
  }

  public Object read() {
    if (_poiCell == null) {
      return null;
    }

    if (_dataType == int.class) {
      return getIntValue();
    }

    return getStringValue();
  }

  private int getIntValue() {
    if (_poiCell.getCellTypeEnum() == CellType.STRING) {
      return Integer.parseInt(_poiCell.getStringCellValue());
    }
    return (int) Math.round(_poiCell.getNumericCellValue());
  }

  private String getStringValue() {
    if (_poiCell.getCellTypeEnum() == CellType.NUMERIC) {
      return Double.toString(_poiCell.getNumericCellValue());
    }
    return _poiCell.getStringCellValue();
  }

  private final Cell _poiCell;

  private final Class<?> _dataType;
}
