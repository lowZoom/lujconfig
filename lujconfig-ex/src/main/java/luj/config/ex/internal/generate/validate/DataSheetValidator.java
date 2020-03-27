package luj.config.ex.internal.generate.validate;

import java.nio.file.Path;
import java.util.List;
import luj.config.ex.api.extract.exception.DuplicateIdException;
import luj.config.ex.api.extract.exception.MissingIdException;
import luj.config.ex.internal.generate.extract.book.ExcelDataExtractor;
import luj.config.ex.internal.generate.validate.id.duplicate.DuplicateIdFinder;
import luj.config.ex.internal.generate.validate.id.missing.MissingIdFinder;

public class DataSheetValidator {

  public DataSheetValidator(ExcelDataExtractor.DataSheet sheet) {
    _sheet = sheet;
  }

  public void validate() {
    missingId();
    duplicateId();
  }

  private void missingId() {
    List<Integer> rowList = new MissingIdFinder(_sheet).find();
    if (rowList.isEmpty()) {
      return;
    }
    throw new MissingIdException(getBookPath(), getSheetName(), rowList);
  }

  private void duplicateId() {
    DuplicateIdFinder.Result result = new DuplicateIdFinder(_sheet).find();
    List<Integer> rowList = result.rowList();
    if (rowList.isEmpty()) {
      return;
    }
    throw new DuplicateIdException(getBookPath(), getSheetName(), result.id(), rowList);
  }

  private Path getBookPath() {
    return _sheet.getWorkbookPath();
  }

  private String getSheetName() {
    return _sheet.getName();
  }

  private final ExcelDataExtractor.DataSheet _sheet;
}
