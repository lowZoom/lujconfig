package luj.config.ex.internal.generate.extract.book;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

public class ExcelDataExtractor {

  public interface DataSheet {

    Header getHeader();

    List<Row> getRowList();

    String getName();

    Path getWorkbookPath();
  }

  public interface Header {

    Map<String, Field> getFieldMap();
  }

  public interface Field {

    String getName();

    boolean isPrimaryKey();
  }

  public interface Row {

    int getRowIndex();

    Map<String, Object> getValueMap();
  }

  public ExcelDataExtractor(Path excelPath, Map<Class<?>, Object> context) {
    _excelPath = excelPath;
    _context = context;
  }

  public List<DataSheet> extract() {
    LOG.debug("{}", _excelPath);

    try (XSSFWorkbook workbook = openExcel(_excelPath)) {
      return extractBook(workbook);

    } catch (IOException | InvalidFormatException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private XSSFWorkbook openExcel(Path path) throws IOException, InvalidFormatException {
    return new XSSFWorkbook(OPCPackage.open(path.toString(), PackageAccess.READ));
  }

  private List<DataSheet> extractBook(XSSFWorkbook book) {
    return ImmutableList.copyOf(book.sheetIterator()).stream()
        .map(this::extractSheet)
        .flatMap(s -> s.map(Stream::of).orElseGet(Stream::empty))
        .collect(Collectors.toList());
  }

  private Optional<DataSheet> extractSheet(Sheet sheet) {
    ConfigHeaderExtractor headerExtract = getBean(ConfigHeaderExtractor.class);
    HeaderColumnExtractor columnExtract = getBean(HeaderColumnExtractor.class);

    return new HeaderExtractInvoker(headerExtract, sheet, columnExtract, _excelPath).invoke()
        .map(h -> extractBody(sheet, h));
  }

  private DataSheet extractBody(Sheet sheet, HeaderExtractInvoker.Header header) {
    HeaderImpl headerOut = new HeaderImpl(header.getColumnList().stream()
        .map(FieldImpl::new)
        .collect(Collectors.toMap(FieldImpl::getName, Function.identity())));

    List<Row> rowList = new DataRowExtractor(sheet, _excelPath, header).extract().stream()
        .map(RowImpl::new)
        .collect(Collectors.toList());

    return new SheetImpl(headerOut, rowList, sheet.getSheetName(), _excelPath);
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
