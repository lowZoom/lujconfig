package luj.config.maven.plugin.excel

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

@PackageScope
@AutoCtor
class ExcelCollector {

  List<String> collect() {
    return Files.walk(_excelRoot)
        .filter { Files.isRegularFile(it) }
        .filter { !it.fileName.toString().startsWith('~$') }
        .map { it.toAbsolutePath().toString() }
        .collect(Collectors.toList())
  }

  private Path _excelRoot
}
