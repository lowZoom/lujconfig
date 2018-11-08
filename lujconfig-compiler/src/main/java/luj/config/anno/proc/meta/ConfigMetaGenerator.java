package luj.config.anno.proc.meta;

import java.io.IOException;
import luj.generate.annotation.process.SingleAnnoProc;

public interface ConfigMetaGenerator {

  interface Factory {

    static ConfigMetaGenerator create(SingleAnnoProc.Context procCtx) {
      return new ConfigMetaGeneratorImpl(new ConfigDeclarationImpl(procCtx.getProcessingType()));
    }
  }

  void generate() throws IOException;
}
