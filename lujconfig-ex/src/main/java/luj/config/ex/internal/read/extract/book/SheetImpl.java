package luj.config.ex.internal.read.extract.book;

import java.nio.file.Path;
import java.util.List;

final class SheetImpl implements ExcelDataExtractor.DataSheet {

  SheetImpl(ExcelDataExtractor.Header header, List<ExcelDataExtractor.Row> rowList,
      String sheetName, Path workbookPath) {
    _header = header;
    _rowList = rowList;
    _sheetName = sheetName;
    _workbookPath = workbookPath;
  }

  @Override
  public ExcelDataExtractor.Header getHeader() {
    return _header;
  }

  @Override
  public List<ExcelDataExtractor.Row> getRowList() {
    return _rowList;
  }

  @Override
  public String getName() {
    return _sheetName;
  }

  @Override
  public Path getWorkbookPath() {
    return _workbookPath;
  }

  private final ExcelDataExtractor.Header _header;
  private final List<ExcelDataExtractor.Row> _rowList;

  private final String _sheetName;
  private final Path _workbookPath;
}
