package luj.config.ex.internal.read.extract.header;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import luj.config.ex.api.error.exception.DuplicateHeaderException;
import luj.config.ex.api.extract.ConfigHeaderExtractor;
import luj.config.ex.api.extract.HeaderColumnExtractor;
import luj.config.ex.internal.read.extract.header.column.ColumnExtractInvoker;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;

public class HeaderExtractInvoker {

  public interface Header {

    int getDataBeginColumn();

    int getDataBeginRow();

    List<Column> getColumnList();
  }

  public interface Column {

    String getFieldName();

    Class<?> getDataType();

    boolean isPrimaryKey();
  }

  public HeaderExtractInvoker(ConfigHeaderExtractor headerExtractor, Sheet poiSheet,
      HeaderColumnExtractor columnExtractor, Path workbookPath) {
    _headerExtractor = headerExtractor;
    _poiSheet = poiSheet;
    _columnExtractor = columnExtractor;
    _workbookPath = workbookPath;
  }

  public Optional<Header> invoke() {
    SheetImpl sheet = new SheetImpl(_poiSheet);
    ContextImpl ctx = new ContextImpl(sheet);

    ExtractReturnImpl headerResult = (ExtractReturnImpl) _headerExtractor.onExtract(ctx);
    if (headerResult == null) {
      // 外部指定跳过
      return Optional.empty();
    }

    List<Column> columnList = extractColumn(headerResult);
    checkDuplicateHeader(columnList);

    return Optional.of(new HeaderImpl(headerResult, columnList));
  }

  private List<Column> extractColumn(ExtractReturnImpl headerResult) {
    int columnCount = IntStream.range(0, headerResult._dataBeginRow)
        .mapToObj(_poiSheet::getRow)
        .filter(Objects::nonNull)
        .mapToInt(Row::getLastCellNum)
        .max()
        .orElse(0);

    int dataBeginColumn = headerResult._dataBeginColumn;
    return IntStream.range(dataBeginColumn, columnCount)
        .mapToObj(i -> invokeAppExtract(i, dataBeginColumn))
        .map(ColumnImpl::new)
        .collect(toList());
  }

  private ColumnExtractInvoker.Result invokeAppExtract(int curCol, int dataBeginCol) {
    return new ColumnExtractInvoker(_columnExtractor,
        dataBeginCol, curCol, _poiSheet, _workbookPath).invoke();
  }

  private void checkDuplicateHeader(List<Column> columnList) {
    columnList.stream()
        .collect(groupingBy(Column::getFieldName)).entrySet().stream()
        .filter(e -> e.getValue().size() > 1)
        .findAny()
        .ifPresent(e -> throwDuplicateHeader(e.getKey(), e.getValue()));
  }

  private void throwDuplicateHeader(String fieldName, List<Column> columnList) {
    List<String> dupCols = columnList.stream()
        .map(c -> (ColumnImpl) c)
        .map(ColumnImpl::getIndex)
        .map(CellReference::convertNumToColString)
        .collect(toList());

    String sheetName = _poiSheet.getSheetName();
    throw new DuplicateHeaderException(_workbookPath, sheetName, fieldName, dupCols);
  }

  private final ConfigHeaderExtractor _headerExtractor;

  private final Sheet _poiSheet;
  private final HeaderColumnExtractor _columnExtractor;

  private final Path _workbookPath;
}
