package luj.config.ex.internal.generate.extract.row;

import static com.google.common.collect.ImmutableMap.toImmutableMap;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import luj.config.ex.api.extract.exception.InvalidValueException;
import luj.config.ex.api.extract.exception.RedundantColumnException;
import luj.config.ex.internal.generate.extract.header.HeaderExtractInvoker;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataRowExtractor {

  public DataRowExtractor(Sheet poiSheet, Path bookPath, HeaderExtractInvoker.Header header) {
    _poiSheet = poiSheet;
    _bookPath = bookPath;
    _header = header;
  }

  public void extract() {
    int rowCount = _poiSheet.getLastRowNum() + 1;

    for (int i = _header.getDataBeginRow(); i < rowCount; i++) {
      Row row = _poiSheet.getRow(i);
      Map<String, Object> valueMap = extractOne(row);

      LOG.debug("{}", valueMap);
    }
  }

  private Map<String, Object> extractOne(Row row) {
    int beginCol = _header.getDataBeginColumn();
    List<HeaderExtractInvoker.Column> columnList = _header.getColumnList();

    int headerColCount = beginCol + columnList.size();
    int dataColCount = row.getLastCellNum();
    if (dataColCount > headerColCount) {
      throw new RedundantColumnException(_bookPath,
          _poiSheet.getSheetName(), headerColCount, dataColCount);
    }

    return IntStream.range(beginCol, dataColCount)
        .mapToObj(i -> makeField(row, i, columnList.get(i - beginCol)))
        .map(this::readValue)
        .filter(f -> f._fieldValue != null)
        .collect(toImmutableMap(f -> f._fieldName, f -> f._fieldValue));
  }

  private Field makeField(Row row, int columnIndex, HeaderExtractInvoker.Column column) {
    Field field = new Field();
    field._headerCol = column;

    field._poiCell = row.getCell(columnIndex);
//    checkNotNull(field._poiCell, "R%sC%s", row.getRowNum() + 1, columnIndex + 1);

    return field;
  }

  private Field readValue(Field field) {
    HeaderExtractInvoker.Column header = field._headerCol;
    field._fieldName = header.getFieldName();

    Cell poiCell = field._poiCell;
    Class<?> dataType = header.getDataType();
    try {
      field._fieldValue = new FieldValueReader(poiCell, dataType).read();
      return field;

    } catch (RuntimeException e) {
      throw new InvalidValueException(e, _bookPath, _poiSheet.getSheetName(), poiCell.getAddress());
    }
  }

  static class Field {

    Cell _poiCell;
    HeaderExtractInvoker.Column _headerCol;

    String _fieldName;
    Object _fieldValue;
  }

  private static final Logger LOG = LoggerFactory.getLogger(DataRowExtractor.class);

  private final Sheet _poiSheet;

  private final Path _bookPath;
  private final HeaderExtractInvoker.Header _header;
}
