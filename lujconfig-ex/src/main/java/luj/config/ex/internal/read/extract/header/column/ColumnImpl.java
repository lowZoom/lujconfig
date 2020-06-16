package luj.config.ex.internal.read.extract.header.column;

import luj.config.ex.api.extract.HeaderColumnExtractor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

final class ColumnImpl implements HeaderColumnExtractor.Column {

  ColumnImpl(int dataBeginColumn, int excelColumn, Sheet poiSheet) {
    _dataBeginColumn = dataBeginColumn;
    _excelColumn = excelColumn;
    _poiSheet = poiSheet;
  }

  @Override
  public int getIndex() {
    return _excelColumn - _dataBeginColumn;
  }

  @Override
  public HeaderColumnExtractor.Cell getCell(int row) {
    Row rowObj = _poiSheet.getRow(row);
    return new CellImpl(rowObj.getCell(_excelColumn, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK));
  }

  private final int _dataBeginColumn;

  private final int _excelColumn;
  private final Sheet _poiSheet;
}
