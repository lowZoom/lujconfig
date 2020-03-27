package luj.config.ex.internal.generate.extract.book;

import java.util.Map;
import luj.config.ex.internal.generate.extract.row.DataRowExtractor;

final class RowImpl implements ExcelDataExtractor.Row {

  RowImpl(DataRowExtractor.DataRow row) {
    _row = row;
  }

  @Override
  public int getRowIndex() {
    return _row.index();
  }

  @Override
  public Map<String, Object> getValueMap() {
    return _row.valueMap();
  }

  private final DataRowExtractor.DataRow _row;
}
