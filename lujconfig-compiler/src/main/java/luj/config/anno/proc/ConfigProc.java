package luj.config.anno.proc;

import com.google.auto.service.AutoService;
import java.io.IOException;
import java.lang.annotation.Annotation;
import javax.annotation.processing.Processor;
import luj.config.anno.Config;
import luj.config.anno.proc.idget.ConfigIdGetGenerator;
import luj.config.anno.proc.json.ConfigImplGenerator;
import luj.config.anno.proc.meta.ConfigMetaGenerator;
import luj.generate.annotation.process.SingleAnnoProc;

@AutoService(Processor.class)
public final class ConfigProc extends SingleAnnoProc {

  @Override
  protected Class<? extends Annotation> supportedAnnotationType() {
    return Config.class;
  }

  @Override
  protected void processElement(Context ctx) throws IOException {
    ConfigImplGenerator.Factory.create(ctx).generate();

    ConfigIdGetGenerator.Factory.create(ctx).generate();
    ConfigMetaGenerator.Factory.create(ctx).generate();
  }
}
