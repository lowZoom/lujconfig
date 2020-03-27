package luj.config.ex.internal.generate.extract.header.column;

import luj.config.ex.api.extract.HeaderColumnExtractor;

final class ReturnImpl implements HeaderColumnExtractor.Return {

  @Override
  public HeaderColumnExtractor.Return fieldName(String val) {
    _fieldName = val;
    return this;
  }

  @Override
  public HeaderColumnExtractor.Return dataType(Class<?> val) {
    _dataType = val;
    return this;
  }

  @Override
  public HeaderColumnExtractor.Return asPrimaryKey(boolean val) {
    _asPrimaryKey = val;
    return this;
  }

  String _fieldName;
  Class<?> _dataType;

  boolean _asPrimaryKey;
}
