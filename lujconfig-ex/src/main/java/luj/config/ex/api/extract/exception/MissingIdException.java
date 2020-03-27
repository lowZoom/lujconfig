package luj.config.ex.api.extract.exception;

import java.nio.file.Path;
import java.util.List;

public class MissingIdException extends RuntimeException {

  public MissingIdException(Path bookPath, String sheet, List<Integer> rowList) {
    _bookPath = bookPath;
    _sheet = sheet;
    _rowList = rowList;
  }

  public Path getBookPath() {
    return _bookPath;
  }

  public String getSheet() {
    return _sheet;
  }

  public List<Integer> getRowList() {
    return _rowList;
  }

  private final Path _bookPath;
  private final String _sheet;

  private final List<Integer> _rowList;
}
