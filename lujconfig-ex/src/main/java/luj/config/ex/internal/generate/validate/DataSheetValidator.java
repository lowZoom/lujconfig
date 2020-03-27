package luj.config.ex.internal.generate.validate;

import java.util.List;
import luj.config.ex.api.extract.exception.DuplicateIdException;
import luj.config.ex.internal.generate.extract.book.ExcelDataExtractor;
import luj.config.ex.internal.generate.validate.id.DuplicateIdFinder;

public class DataSheetValidator {

  public DataSheetValidator(ExcelDataExtractor.DataSheet sheet) {
    _sheet = sheet;
  }

  public void validate() {
    checkDuplicateId();
  }

  private void checkDuplicateId() {
    DuplicateIdFinder.Result result = new DuplicateIdFinder(_sheet).find();
    List<Integer> rowList = result.rowList();

    if (rowList.isEmpty()) {
      return;
    }
    throw new DuplicateIdException(result.id(), rowList);
  }

  private final ExcelDataExtractor.DataSheet _sheet;
}
