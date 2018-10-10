package luj.config.maven.plugin;

import org.apache.maven.plugin.Mojo;
import org.junit.Assert;
import org.junit.Test;

/**
 * @see ExcelToJsonMojo
 */
public class ExcelToJsonMojoTest {

  @Test
  public void testMojo() {
    Assert.assertTrue(Mojo.class.isAssignableFrom(ExcelToJsonMojo.class));
  }
}
