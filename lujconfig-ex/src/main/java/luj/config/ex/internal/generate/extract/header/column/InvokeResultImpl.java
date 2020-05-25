package luj.config.ex.internal.generate.extract.header.column;

final class InvokeResultImpl implements ColumnExtractInvoker.Result {

  @Override
  public String fieldName() {
    return _return._fieldName;
  }

  @Override
  public Class<?> dataType() {
    return _return._dataType;
  }

  @Override
  public boolean isPrimaryKey() {
    return _return._asPrimaryKey;
  }

  @Override
  public int columnIndex() {
    return _columnIndex;
  }

  ReturnImpl _return;
  int _columnIndex;
}
