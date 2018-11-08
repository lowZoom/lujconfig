package luj.config.anno.proc.idget;

import java.io.IOException;
import luj.generate.annotation.process.SingleAnnoProc;

public interface ConfigIdGetGenerator {

  interface Factory {

    static ConfigIdGetGenerator create(SingleAnnoProc.Context procCtx) {
      return new ConfigIdGetGeneratorImpl(procCtx.getProcessingType());
    }
  }

  void generate() throws IOException;
}
