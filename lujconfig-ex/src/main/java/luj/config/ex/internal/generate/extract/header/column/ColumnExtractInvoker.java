package luj.config.ex.internal.generate.extract.header.column;

import luj.config.ex.api.extract.HeaderColumnExtractor;
import org.apache.poi.ss.usermodel.Sheet;

public class ColumnExtractInvoker {

  public interface Result {

    String fieldName();

    Class<?> dataType();

    boolean isPrimaryKey();
  }

  public ColumnExtractInvoker(HeaderColumnExtractor columnExtractor, int dataBeginColumn,
      int columnIndex, Sheet poiSheet) {
    _columnExtractor = columnExtractor;
    _dataBeginColumn = dataBeginColumn;
    _columnIndex = columnIndex;
    _poiSheet = poiSheet;
  }

  public Result invoke() {
    ColumnImpl column = new ColumnImpl(_dataBeginColumn, _columnIndex, _poiSheet);
    ContextImpl ctx = new ContextImpl(column);

    ReturnImpl aReturn = (ReturnImpl) _columnExtractor.onExtract(ctx);
    return new InvokeResultImpl(aReturn);
  }

  private final HeaderColumnExtractor _columnExtractor;
  private final int _dataBeginColumn;

  private final int _columnIndex;
  private final Sheet _poiSheet;
}
