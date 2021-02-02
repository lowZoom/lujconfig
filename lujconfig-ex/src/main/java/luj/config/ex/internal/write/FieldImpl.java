package luj.config.ex.internal.write;

import luj.config.ex.api.out.ConfigFileWriter;
import luj.config.ex.internal.read.extract.book.ExcelDataExtractor;

final class FieldImpl implements ConfigFileWriter.Field {

  FieldImpl(ExcelDataExtractor.Field field) {
    _field = field;
  }

  @Override
  public String getName() {
    return _field.getName();
  }

  @Override
  public boolean isPrimaryKey() {
    return _field.isPrimaryKey();
  }

  private final ExcelDataExtractor.Field _field;
}
