package luj.config.ex.internal.generate.extract.header.column;

import luj.config.ex.api.extract.HeaderColumnExtractor;

final class ContextImpl implements HeaderColumnExtractor.Context {

  ContextImpl(HeaderColumnExtractor.Column column) {
    _column = column;
  }

  @Override
  public HeaderColumnExtractor.Column getColumn() {
    return _column;
  }

  @Override
  public HeaderColumnExtractor.Return returnColumn() {
    return new ReturnImpl();
  }

  private final HeaderColumnExtractor.Column _column;
}
