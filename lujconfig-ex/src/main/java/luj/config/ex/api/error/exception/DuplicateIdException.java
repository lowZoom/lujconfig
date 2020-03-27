package luj.config.ex.api.error.exception;

import java.nio.file.Path;
import java.util.List;

public class DuplicateIdException extends RuntimeException {

  public DuplicateIdException(Path bookPath, String sheet, Object id, List<Integer> rowList) {
    _bookPath = bookPath;
    _sheet = sheet;
    _id = id;
    _rowList = rowList;
  }

  public Path getBookPath() {
    return _bookPath;
  }

  public String getSheet() {
    return _sheet;
  }

  public Object getId() {
    return _id;
  }

  public List<Integer> getRowList() {
    return _rowList;
  }

  private final Path _bookPath;
  private final String _sheet;

  private final Object _id;
  private final List<Integer> _rowList;
}
