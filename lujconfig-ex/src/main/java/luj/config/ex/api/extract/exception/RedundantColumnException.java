package luj.config.ex.api.extract.exception;

import java.nio.file.Path;

public class RedundantColumnException extends RuntimeException {

  public RedundantColumnException(Path bookPath, String sheet, int headerColumnCount,
      int dataColumnCount) {
    _bookPath = bookPath;
    _sheet = sheet;
    _headerColumnCount = headerColumnCount;
    _dataColumnCount = dataColumnCount;
  }

  public Path getBookPath() {
    return _bookPath;
  }

  public String getSheet() {
    return _sheet;
  }

  public int getHeaderColumnCount() {
    return _headerColumnCount;
  }

  public int getDataColumnCount() {
    return _dataColumnCount;
  }

  private final Path _bookPath;
  private final String _sheet;

  private final int _headerColumnCount;
  private final int _dataColumnCount;
}
