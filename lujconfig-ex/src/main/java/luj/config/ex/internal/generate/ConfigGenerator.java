package luj.config.ex.internal.generate;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class ConfigGenerator {

  public ConfigGenerator(Path excelDir, Map<Class<?>, Object> context) {
    _excelDir = excelDir;
    _context = context;
  }

  public void generate() {
    List<Path> excelList = new ExcelPathCollector(_excelDir).collect();

    for (Path path : excelList) {
      new ExcelDataExtractor(path, _context).extract();
    }
  }

  private final Path _excelDir;

  private final Map<Class<?>, Object> _context;
}
