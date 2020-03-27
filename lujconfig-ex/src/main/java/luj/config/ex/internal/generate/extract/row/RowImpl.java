package luj.config.ex.internal.generate.extract.row;

import java.util.Map;

final class RowImpl implements DataRowExtractor.DataRow {

  RowImpl(int index, Map<String, Object> valueMap) {
    _index = index;
    _valueMap = valueMap;
  }

  @Override
  public int index() {
    return _index;
  }

  @Override
  public Map<String, Object> valueMap() {
    return _valueMap;
  }

  private final int _index;

  private final Map<String, Object> _valueMap;
}
