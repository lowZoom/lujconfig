package luj.config.ex.internal.read.validate.id;

import java.util.Optional;
import luj.config.ex.internal.read.extract.book.ExcelDataExtractor;

public class IdFieldNameFinder {

  public IdFieldNameFinder(ExcelDataExtractor.DataSheet sheet) {
    _sheet = sheet;
  }

  public Optional<String> find() {
    return _sheet.getHeader().getFieldMap().values().stream()
        .filter(ExcelDataExtractor.Field::isPrimaryKey)
        .findAny()
        .map(ExcelDataExtractor.Field::getName);
  }

  private final ExcelDataExtractor.DataSheet _sheet;
}
