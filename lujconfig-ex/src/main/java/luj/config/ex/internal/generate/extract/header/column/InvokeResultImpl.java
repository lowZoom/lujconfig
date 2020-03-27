package luj.config.ex.internal.generate.extract.header.column;

final class InvokeResultImpl implements ColumnExtractInvoker.Result {

  InvokeResultImpl(ReturnImpl aReturn) {
    _return = aReturn;
  }

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

  private final ReturnImpl _return;
}
