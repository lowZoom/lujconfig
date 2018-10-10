package luj.config.maven.plugin;

import luj.config.maven.plugin.excel.ExcelToJson;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

/**
 * @see <a href="https://github.com/groovy/groovy-eclipse/wiki/Groovy-Eclipse-Maven-plugin#do-almost-nothing">本类存在原因</a>
 */
@Mojo(name = "excel-to-json")
final class ExcelToJsonMojo extends AbstractMojo {

  @Parameter(defaultValue = "${project}", readonly = true)
  private MavenProject project;

  @Override
  public void execute() {
    new ExcelToJson(project).execute();
  }
}
