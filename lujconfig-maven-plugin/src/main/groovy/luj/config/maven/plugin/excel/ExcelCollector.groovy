package luj.config.maven.plugin.excel

import groovy.transform.PackageScope

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

@PackageScope
class ExcelCollector {

  ExcelCollector(Path excelRoot) {
    _excelRoot = excelRoot
  }

  List<Path> collect() {
    return Files.walk(_excelRoot)
        .filter { Files.isRegularFile(it) }
        .filter { !it.fileName.toString().startsWith('~$') }
        .map { it.toAbsolutePath() }
        .collect(Collectors.toList())
  }

  private final Path _excelRoot
}
