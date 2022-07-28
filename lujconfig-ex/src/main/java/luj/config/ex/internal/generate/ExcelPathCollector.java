package luj.config.ex.internal.generate;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import luj.ava.file.path.PathX;

public class ExcelPathCollector {

  public ExcelPathCollector(Path excelDir) {
    _excelDir = excelDir;
  }

  public List<Path> collect() {
    return PathX.of(_excelDir).walk(s -> s
        .filter(PathX::isRegularFile)
        .filter(p -> p.getFileName().endsWith(".xlsx"))
        .filter(p -> !p.getFileName().startsWith("~$"))
        .map(PathX::asPath)
        .collect(Collectors.toList()));
  }

  private final Path _excelDir;
}
