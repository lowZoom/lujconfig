package luj.config.ex.internal.generate;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import luj.config.ex.internal.generate.extract.book.ExcelDataExtractor;
import luj.config.ex.internal.generate.validate.DataSheetValidator;

public class ConfigGenerator {

  public ConfigGenerator(Path excelDir, Map<Class<?>, Object> context) {
    _excelDir = excelDir;
    _context = context;
  }

  public void generate() {
    new ExcelPathCollector(_excelDir).collect().stream()
        .map(p -> new ExcelDataExtractor(p, _context).extract())
        .flatMap(Collection::stream)
        .forEach(s -> new DataSheetValidator(s).validate());
  }

  private final Path _excelDir;

  private final Map<Class<?>, Object> _context;
}
