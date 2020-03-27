package luj.config.ex.internal.generate.validate.id.missing;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.stream.Collectors;
import luj.config.ex.internal.generate.extract.book.ExcelDataExtractor;
import luj.config.ex.internal.generate.validate.id.IdFieldNameFinder;

public class MissingIdFinder {

  public MissingIdFinder(ExcelDataExtractor.DataSheet sheet) {
    _sheet = sheet;
  }

  public List<Integer> find() {
    return new IdFieldNameFinder(_sheet).find()
        .map(this::findImpl)
        .orElseGet(ImmutableList::of);
  }

  private List<Integer> findImpl(String idField) {
    return _sheet.getRowList().stream()
        .filter(r -> !r.getValueMap().isEmpty())
        .filter(r -> r.getValueMap().get(idField) == null)
        .map(r -> r.getRowIndex() + 1)
        .collect(Collectors.toList());
  }

  private final ExcelDataExtractor.DataSheet _sheet;
}
