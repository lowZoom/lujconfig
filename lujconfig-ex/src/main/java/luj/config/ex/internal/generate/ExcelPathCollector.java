package luj.config.ex.internal.generate;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import luj.ava.file.path.PathX;

final class ExcelPathCollector {

  ExcelPathCollector(Path excelDir) {
    _excelDir = excelDir;
  }

  public List<Path> collect() {
    Stream<Path> walker = startWalk();
    List<Path> excelList = walker
        .filter(p -> Files.isRegularFile(p))
        .map(PathX::of)
        .filter(p -> p.getFileName().endsWith(".xlsx"))
        .filter(p -> !p.getFileName().startsWith("~$"))
        .map(PathX::asPath)
        .collect(toList());

    walker.close();
    return excelList;
  }

  private Stream<Path> startWalk() {
    try {
      return Files.walk(_excelDir);

    } catch (IOException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private final Path _excelDir;
}
