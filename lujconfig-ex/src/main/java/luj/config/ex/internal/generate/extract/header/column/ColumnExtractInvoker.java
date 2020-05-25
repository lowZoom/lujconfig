package luj.config.ex.internal.generate.extract.header.column;

import java.nio.file.Path;
import luj.config.ex.api.extract.HeaderColumnExtractor;
import org.apache.poi.ss.usermodel.Sheet;

public class ColumnExtractInvoker {

  public interface Result {

    String fieldName();

    Class<?> dataType();

    boolean isPrimaryKey();

    int columnIndex();
  }

  public ColumnExtractInvoker(HeaderColumnExtractor columnExtractor, int dataBeginColumn,
      int columnIndex, Sheet poiSheet, Path workbookPath) {
    _columnExtractor = columnExtractor;
    _dataBeginColumn = dataBeginColumn;
    _columnIndex = columnIndex;
    _poiSheet = poiSheet;
    _workbookPath = workbookPath;
  }

  public Result invoke() {
    ErrorContextImpl errorContext = new ErrorContextImpl(_workbookPath,
        _poiSheet.getSheetName(), -1, _columnIndex + 1);

    ColumnImpl column = new ColumnImpl(_dataBeginColumn, _columnIndex, _poiSheet);
    ExtractContextImpl ctx = new ExtractContextImpl(column, errorContext);

    InvokeResultImpl result = new InvokeResultImpl();
    result._return = (ReturnImpl) _columnExtractor.onExtract(ctx);
    result._columnIndex = _columnIndex;

    return result;
  }

  private final HeaderColumnExtractor _columnExtractor;
  private final int _dataBeginColumn;

  private final int _columnIndex;
  private final Sheet _poiSheet;

  private final Path _workbookPath;
}
