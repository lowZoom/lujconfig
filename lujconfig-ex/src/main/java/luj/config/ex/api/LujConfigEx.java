package luj.config.ex.api;

import java.nio.file.Path;
import java.util.Map;
import luj.config.ex.internal.generate.ConfigGenerator;

public enum LujConfigEx {
  ;

  public static void generate(Path excelDir, Map<Class<?>, Object> context) {
    new ConfigGenerator(excelDir, context).generate();
  }
}
