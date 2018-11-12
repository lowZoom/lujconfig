package luj.config.maven.plugin.excel

import org.apache.maven.project.MavenProject

import java.nio.file.Path
import java.nio.file.Paths

class ExcelToJson {

  ExcelToJson(MavenProject project) {
    _project = project
  }

  void execute() {
    Path excelRoot = Paths.get(_project.basedir.absolutePath, 'dat', 'excel')

    Path outputRoot = excelRoot.resolveSibling('json')
    cleanOutput(outputRoot.toString())

    new ExcelCollector(excelRoot).collect().each {
      new ExcelReader(it, outputRoot).read().each {
        it.writeJsonFile()
      }
    }
  }

  private void cleanOutput(String outputPath) {
    new AntBuilder().with {
      mkdir(dir: outputPath)

      delete(includeemptydirs: true, verbose: true) {
        fileset(dir: outputPath, includes: '**/*')
      }
    }
  }

  private final MavenProject _project
}
