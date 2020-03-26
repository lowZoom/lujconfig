package luj.config.ex.internal.generate.extract.header.column;

import luj.config.ex.api.extract.HeaderColumnExtractor;
import org.apache.poi.ss.usermodel.Sheet;

final class ColumnImpl implements HeaderColumnExtractor.Column {

  ColumnImpl(int columnIndex, Sheet poiSheet) {
    _columnIndex = columnIndex;
    _poiSheet = poiSheet;
  }

  @Override
  public HeaderColumnExtractor.Cell getCell(int row) {
    return new CellImpl(_poiSheet.getRow(row).getCell(_columnIndex));
  }

  private final int _columnIndex;

  private final Sheet _poiSheet;
}
