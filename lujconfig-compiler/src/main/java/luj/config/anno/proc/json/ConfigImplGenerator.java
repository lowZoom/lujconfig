package luj.config.anno.proc.json;

import java.io.IOException;
import luj.generate.annotation.process.SingleAnnoProc;

public interface ConfigImplGenerator {

  interface Factory {

    static ConfigImplGenerator create(SingleAnnoProc.Context procCtx) {
      ConfigTypeImpl type = new ConfigTypeImpl(procCtx.getProcessingType());
      return new ConfigImplGeneratorImpl(type);
    }
  }

  void generate() throws IOException;
}
