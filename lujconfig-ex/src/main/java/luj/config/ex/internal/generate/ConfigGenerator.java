package luj.config.ex.internal.generate;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import luj.config.ex.internal.read.extract.book.ExcelDataExtractor;
import luj.config.ex.internal.read.validate.DataSheetValidator;
import luj.config.ex.internal.write.SheetToFileWriter;

public class ConfigGenerator {

  public ConfigGenerator(Path excelDir, Map<Class<?>, Object> context) {
    _excelDir = excelDir;
    _context = context;
  }

  public void generate() {
    new ExcelPathCollector(_excelDir).collect().stream()
        .map(p -> new ExcelDataExtractor(p, _context).extract())
        .flatMap(Collection::stream)
        .map(s -> new DataSheetValidator(s).validate())
        .forEach(s -> new SheetToFileWriter(s, _context).write());
  }

  private final Path _excelDir;

  private final Map<Class<?>, Object> _context;
}
