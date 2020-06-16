package luj.config.ex.internal.read.extract.header;

import luj.config.ex.internal.read.extract.header.column.ColumnExtractInvoker;

final class ColumnImpl implements HeaderExtractInvoker.Column {

  ColumnImpl(ColumnExtractInvoker.Result columnResult) {
    _columnResult = columnResult;
  }

  public int getIndex() {
    return _columnResult.columnIndex();
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
  public boolean isPrimaryKey() {
    return _columnResult.isPrimaryKey();
  }

  @Override
  public String toString() {
    return getFieldName();
  }

  private final ColumnExtractInvoker.Result _columnResult;
}
