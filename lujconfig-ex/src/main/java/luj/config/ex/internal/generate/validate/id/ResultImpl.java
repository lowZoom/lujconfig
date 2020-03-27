package luj.config.ex.internal.generate.validate.id;

import java.util.List;

final class ResultImpl implements DuplicateIdFinder.Result {

  ResultImpl(Object id, List<Integer> rowList) {
    _id = id;
    _rowList = rowList;
  }

  @Override
  public Object id() {
    return _id;
  }

  @Override
  public List<Integer> rowList() {
    return _rowList;
  }

  private final Object _id;

  private final List<Integer> _rowList;
}
