package luj.config.ex.internal.generate.extract.header.column;

import luj.config.ex.api.extract.HeaderColumnExtractor;

final class ContextImpl implements HeaderColumnExtractor.Context {

  ContextImpl(HeaderColumnExtractor.Column column, HeaderColumnExtractor.Return aReturn) {
    _column = column;
    _return = aReturn;
  }

  @Override
  public HeaderColumnExtractor.Column getColumn() {
    return _column;
  }

  @Override
  public HeaderColumnExtractor.Return returnColumn() {
    return _return;
  }

  private final HeaderColumnExtractor.Column _column;

  private final HeaderColumnExtractor.Return _return;
}
