package luj.config.ex.internal.read.extract.header.column;

import luj.config.ex.api.extract.HeaderColumnExtractor;
import org.apache.poi.ss.usermodel.Cell;

final class CellImpl implements HeaderColumnExtractor.Cell {

  CellImpl(Cell poiCell) {
    _poiCell = poiCell;
  }

  @Override
  public String getStringValue() {
    return _poiCell.getStringCellValue();
  }

  private final Cell _poiCell;
}
