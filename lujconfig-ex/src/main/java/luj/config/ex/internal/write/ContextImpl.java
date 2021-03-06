package luj.config.ex.internal.write;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import luj.config.ex.api.out.ConfigFileWriter;
import luj.config.ex.internal.read.extract.book.ExcelDataExtractor;

final class ContextImpl implements ConfigFileWriter.Context, ConfigFileWriter.Sheet {

  @Override
  public ConfigFileWriter.Sheet getSheet() {
    return this;
  }

  @Override
  public String getName() {
    return _sheet.getName();
  }

  @Override
  public List<ConfigFileWriter.Field> getHeader() {
    return _sheet.getHeader().getFieldMap().values().stream()
        .map(FieldImpl::new)
        .collect(Collectors.toList());
  }

  @Override
  public List<ConfigFileWriter.Row> getRowList() {
    return _sheet.getRowList().stream()
        .map(RowImpl::new)
        .collect(Collectors.toList());
  }

  @Override
  public Path getWorkbookPath() {
    return _sheet.getWorkbookPath();
  }

  ExcelDataExtractor.DataSheet _sheet;
}
