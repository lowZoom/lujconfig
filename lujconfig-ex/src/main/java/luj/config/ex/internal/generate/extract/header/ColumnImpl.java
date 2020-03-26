package luj.config.ex.internal.generate.extract.header;

import luj.config.ex.internal.generate.extract.header.column.ColumnExtractInvoker;

final class ColumnImpl implements HeaderExtractInvoker.Column {

  ColumnImpl(ColumnExtractInvoker.Result columnResult) {
    _columnResult = columnResult;
  }

  @Override
  public String getFieldName() {
    return _columnResult.fieldName();
  }

  @Override
  public Class<?> getDataType() {
    return _columnResult.dataType();
  }

  @Override
  public String toString() {
    return getFieldName();
  }

  private final ColumnExtractInvoker.Result _columnResult;
}
