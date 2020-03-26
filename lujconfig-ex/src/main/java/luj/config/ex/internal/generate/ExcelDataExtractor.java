package luj.config.ex.internal.generate;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import luj.config.ex.api.extract.ConfigHeaderExtractor;
import luj.config.ex.api.extract.HeaderColumnExtractor;
import luj.config.ex.internal.generate.extract.header.HeaderExtractInvoker;
import luj.config.ex.internal.generate.extract.row.DataRowExtractor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class ExcelDataExtractor {

  ExcelDataExtractor(Path excelPath, Map<Class<?>, Object> context) {
    _excelPath = excelPath;
    _context = context;
  }

  public void extract() {
    LOG.debug("{}", _excelPath);

    try (XSSFWorkbook workbook = openExcel(_excelPath)) {
      extractBook(workbook);

    } catch (IOException | InvalidFormatException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private XSSFWorkbook openExcel(Path path) throws IOException, InvalidFormatException {
    return new XSSFWorkbook(OPCPackage.open(path.toString(), PackageAccess.READ));
  }

  private void extractBook(XSSFWorkbook book) {
    Iterator<Sheet> sheetIter = book.sheetIterator();

    while (sheetIter.hasNext()) {
      Sheet sheet = sheetIter.next();
      extractSheet(sheet);
    }
  }

  private void extractSheet(Sheet sheet) {
    ConfigHeaderExtractor headerExtract = getBean(ConfigHeaderExtractor.class);
    HeaderColumnExtractor columnExtract = getBean(HeaderColumnExtractor.class);

    new HeaderExtractInvoker(headerExtract, sheet, columnExtract).invoke()
        .ifPresent(h -> new DataRowExtractor(sheet, _excelPath, h).extract());
  }

  @SuppressWarnings("unchecked")
  private <T> T getBean(Class<T> beanType) {
    Object bean = _context.get(beanType);
    return (T) checkNotNull(bean, beanType.getName());
  }

  private static final Logger LOG = LoggerFactory.getLogger(ExcelDataExtractor.class);

  private final Path _excelPath;
  private final Map<Class<?>, Object> _context;
}
