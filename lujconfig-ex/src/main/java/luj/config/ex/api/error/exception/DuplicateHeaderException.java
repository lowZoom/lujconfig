package luj.config.ex.api.error.exception;

import java.nio.file.Path;
import java.util.List;

public class DuplicateHeaderException extends RuntimeException {

  public DuplicateHeaderException(Path bookPath, String sheet, String headerName,
      List<String> columnList) {
    _bookPath = bookPath;
    _sheet = sheet;
    _headerName = headerName;
    _columnList = columnList;
  }

  public Path getBookPath() {
    return _bookPath;
  }

  public String getSheet() {
    return _sheet;
  }

  public String getHeaderName() {
    return _headerName;
  }

  public List<String> getColumnList() {
    return _columnList;
  }

  private final Path _bookPath;
  private final String _sheet;

  private final String _headerName;
  private final List<String> _columnList;
}
