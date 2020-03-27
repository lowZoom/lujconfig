package luj.config.ex.internal.generate.extract.book;

import java.util.List;

final class SheetImpl implements ExcelDataExtractor.DataSheet {

  SheetImpl(ExcelDataExtractor.Header header, List<ExcelDataExtractor.Row> rowList) {
    _header = header;
    _rowList = rowList;
  }

  @Override
  public ExcelDataExtractor.Header getHeader() {
    return _header;
  }

  @Override
  public List<ExcelDataExtractor.Row> getRowList() {
    return _rowList;
  }

  private final ExcelDataExtractor.Header _header;

  private final List<ExcelDataExtractor.Row> _rowList;
}
