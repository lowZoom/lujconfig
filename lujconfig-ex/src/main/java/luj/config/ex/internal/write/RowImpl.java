package luj.config.ex.internal.write;

import java.util.Map;
import luj.config.ex.api.out.ConfigFileWriter;
import luj.config.ex.internal.read.extract.book.ExcelDataExtractor;

final class RowImpl implements ConfigFileWriter.Row {

  RowImpl(ExcelDataExtractor.Row row) {
    _row = row;
  }

  @Override
  public int getIndex() {
    return _row.getRowIndex();
  }

  @Override
  public Map<String, Object> getDataMap() {
    return _row.getValueMap();
  }

  private final ExcelDataExtractor.Row _row;
}
