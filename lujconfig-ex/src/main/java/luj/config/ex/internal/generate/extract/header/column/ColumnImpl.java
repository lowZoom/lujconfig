package luj.config.ex.internal.generate.extract.header.column;

import luj.config.ex.api.extract.HeaderColumnExtractor;
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
    return new CellImpl(_poiSheet.getRow(row).getCell(_excelColumn));
  }

  private final int _dataBeginColumn;

  private final int _excelColumn;
  private final Sheet _poiSheet;
}
