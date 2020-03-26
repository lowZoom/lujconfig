package luj.config.ex.internal.generate.extract.header;

import luj.config.ex.api.extract.ConfigHeaderExtractor;

final class ExtractReturnImpl implements ConfigHeaderExtractor.Return {

  @Override
  public ConfigHeaderExtractor.Return dataBeginColumn(int val) {
    _dataBeginColumn = val;
    return this;
  }

  @Override
  public ConfigHeaderExtractor.Return dataBeginRow(int val) {
    _dataBeginRow = val;
    return this;
  }

  int _dataBeginColumn;

  int _dataBeginRow;
}
