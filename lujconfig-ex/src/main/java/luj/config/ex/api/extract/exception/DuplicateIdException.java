package luj.config.ex.api.extract.exception;

import java.util.List;

public class DuplicateIdException extends RuntimeException {

  public DuplicateIdException(Object id, List<Integer> rowList) {
    _id = id;
    _rowList = rowList;
  }

  public Object getId() {
    return _id;
  }

  public List<Integer> getRowList() {
    return _rowList;
  }

  private final Object _id;

  private final List<Integer> _rowList;
}
