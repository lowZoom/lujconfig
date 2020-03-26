package luj.config.ex.internal.generate.extract.header.column;

import luj.config.ex.api.extract.HeaderColumnExtractor;
import org.apache.poi.ss.usermodel.Sheet;

public class ColumnExtractInvoker {

  public interface Result {

    String fieldName();

    Class<?> dataType();
  }

  public ColumnExtractInvoker(HeaderColumnExtractor columnExtractor, int columnIndex,
      Sheet poiSheet) {
    _columnExtractor = columnExtractor;
    _columnIndex = columnIndex;
    _poiSheet = poiSheet;
  }

  public Result invoke() {
    ColumnImpl column = new ColumnImpl(_columnIndex, _poiSheet);
    ReturnImpl aReturn = new ReturnImpl();

    ContextImpl ctx = new ContextImpl(column, aReturn);
    _columnExtractor.onExtract(ctx);

    return new InvokeResultImpl(aReturn);
  }

  private final HeaderColumnExtractor _columnExtractor;

  private final int _columnIndex;
  private final Sheet _poiSheet;
}
