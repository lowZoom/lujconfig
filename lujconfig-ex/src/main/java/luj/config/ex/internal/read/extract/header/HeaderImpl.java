package luj.config.ex.internal.read.extract.header;

import java.util.List;

final class HeaderImpl implements HeaderExtractInvoker.Header {

  HeaderImpl(ExtractReturnImpl headerResult, List<HeaderExtractInvoker.Column> columnList) {
    _headerResult = headerResult;
    _columnList = columnList;
  }

  @Override
  public int getDataBeginColumn() {
    return _headerResult._dataBeginColumn;
  }

  @Override
  public int getDataBeginRow() {
    return _headerResult._dataBeginRow;
  }

  @Override
  public List<HeaderExtractInvoker.Column> getColumnList() {
    return _columnList;
  }

  private final ExtractReturnImpl _headerResult;

  private final List<HeaderExtractInvoker.Column> _columnList;
}
