package luj.config.ex.internal.generate.extract.header.column;

import java.nio.file.Path;
import luj.config.ex.api.error.CellErrorMaker;

final class ErrorContextImpl implements CellErrorMaker.Context {

  ErrorContextImpl(Path workbookPath, String sheetName, int rowNum, int columnNum) {
    _workbookPath = workbookPath;
    _sheetName = sheetName;
    _rowNum = rowNum;
    _columnNum = columnNum;
  }

  @Override
  public Path getWorkbookPath() {
    return _workbookPath;
  }

  @Override
  public String getSheetName() {
    return _sheetName;
  }

  @Override
  public int getRowNum() {
    return _rowNum;
  }

  @Override
  public int getColumnNum() {
    return _columnNum;
  }

  private final Path _workbookPath;
  private final String _sheetName;

  private final int _rowNum;
  private final int _columnNum;
}
