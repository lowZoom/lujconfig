package luj.config.ex.internal.generate.extract.book;

import luj.config.ex.internal.generate.extract.header.HeaderExtractInvoker;

final class FieldImpl implements ExcelDataExtractor.Field {

  FieldImpl(HeaderExtractInvoker.Column column) {
    _column = column;
  }

  @Override
  public String getName() {
    return _column.getFieldName();
  }

  @Override
  public boolean isPrimaryKey() {
    return _column.isPrimaryKey();
  }

  @Override
  public String toString() {
    return "Field[name=" + getName() + "]";
  }

  private final HeaderExtractInvoker.Column _column;
}
