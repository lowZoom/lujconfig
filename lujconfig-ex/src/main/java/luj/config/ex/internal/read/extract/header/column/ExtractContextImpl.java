package luj.config.ex.internal.read.extract.header.column;

import luj.config.ex.api.error.CellErrorMaker;
import luj.config.ex.api.extract.HeaderColumnExtractor;

final class ExtractContextImpl implements HeaderColumnExtractor.Context {

  ExtractContextImpl(HeaderColumnExtractor.Column column, CellErrorMaker.Context errorContext) {
    _column = column;
    _errorContext = errorContext;
  }

  @Override
  public HeaderColumnExtractor.Column getColumn() {
    return _column;
  }

  @Override
  public HeaderColumnExtractor.Return returnColumn() {
    return new ReturnImpl();
  }

  @Override
  public RuntimeException error(CellErrorMaker maker) {
    return maker.make(_errorContext);
  }

  private final HeaderColumnExtractor.Column _column;

  private final CellErrorMaker.Context _errorContext;
}
