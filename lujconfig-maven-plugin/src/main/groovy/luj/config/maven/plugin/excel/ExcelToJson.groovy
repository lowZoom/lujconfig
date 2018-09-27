package luj.config.maven.plugin.excel

import org.apache.maven.project.MavenProject

import java.nio.file.Paths

class ExcelToJson {

  ExcelToJson(MavenProject project) {
    _project = project
  }

  void execute() {
    def excelRoot = Paths.get(_project.basedir.absolutePath, 'dat', 'excel')

    new ExcelCollector(excelRoot).collect()
        .collect { new ExcelReader(it).read() }
        .flatten()
        .each { ExcelReader.Sheet s -> s.writeJsonFile() }
  }

  private MavenProject _project
}
