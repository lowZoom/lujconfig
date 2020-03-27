package luj.config.ex.internal.generate.validate.id;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.stream.Collectors;
import luj.config.ex.internal.generate.extract.book.ExcelDataExtractor;

public class DuplicateIdFinder {

  public interface Result {

    Object id();

    List<Integer> rowList();
  }

  public DuplicateIdFinder(ExcelDataExtractor.DataSheet sheet) {
    _sheet = sheet;
  }

  public Result find() {
    return _sheet.getHeader().getFieldMap().values().stream()
        .filter(ExcelDataExtractor.Field::isPrimaryKey)
        .findAny()
        .map(ExcelDataExtractor.Field::getName)
        .map(this::findImpl)
        .orElseGet(this::notFound);
  }

  private Result findImpl(String idField) {
    return _sheet.getRowList().stream()
        .collect(groupingBy(r -> r.getValueMap().get(idField)))
        .entrySet().stream()
        .filter(e -> e.getValue().size() > 1)
        .max(comparing(e -> e.getValue().size()))
        .map(e -> toResult(e.getKey(), e.getValue()))
        .orElseGet(this::notFound);
  }

  private Result notFound() {
    return new ResultImpl(null, ImmutableList.of());
  }

  private Result toResult(Object id, List<ExcelDataExtractor.Row> rowList) {
    return new ResultImpl(id, rowList.stream()
        .map(ExcelDataExtractor.Row::getRowIndex)
        .collect(Collectors.toList()));
  }

  private final ExcelDataExtractor.DataSheet _sheet;
}
