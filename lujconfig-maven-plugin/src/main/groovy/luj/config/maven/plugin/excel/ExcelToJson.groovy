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
        .collect { it as ExcelReader.Sheet }
        .each { it.writeJsonFile() }
  }

  private MavenProject _project
}
