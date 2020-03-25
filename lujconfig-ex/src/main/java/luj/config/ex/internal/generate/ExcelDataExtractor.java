package luj.config.ex.internal.generate;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import luj.config.ex.api.extract.ConfigHeaderExtractor;
import luj.config.ex.internal.generate.header.HeaderExtractInvoker;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

final class ExcelDataExtractor {

  ExcelDataExtractor(Path excelPath, Map<Class<?>, Object> context) {
    _excelPath = excelPath;
    _context = context;
  }

  public void extract() {
    try (XSSFWorkbook workbook = new XSSFWorkbook(_excelPath.toString())) {
      extractBook(workbook);

    } catch (IOException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private void extractBook(XSSFWorkbook book) {
    Iterator<Sheet> sheetIter = book.sheetIterator();

    while (sheetIter.hasNext()) {
      Sheet sheet = sheetIter.next();
      extractSheet(sheet);
    }
  }

  private void extractSheet(Sheet sheet) {
    new HeaderExtractInvoker(getBean(ConfigHeaderExtractor.class), sheet).invoke();
  }

  @SuppressWarnings("unchecked")
  private <T> T getBean(Class<?> beanType) {
    Object bean = _context.get(beanType);
    return (T) checkNotNull(bean, beanType.getName());
  }

  private final Path _excelPath;

  private final Map<Class<?>, Object> _context;
}
