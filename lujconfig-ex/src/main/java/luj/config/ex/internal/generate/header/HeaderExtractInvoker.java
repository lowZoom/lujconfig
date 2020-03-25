package luj.config.ex.internal.generate.header;

import luj.config.ex.api.extract.ConfigHeaderExtractor;
import org.apache.poi.ss.usermodel.Sheet;

public class HeaderExtractInvoker {

  public HeaderExtractInvoker(ConfigHeaderExtractor headerExtractor, Sheet poiSheet) {
    _headerExtractor = headerExtractor;
    _poiSheet = poiSheet;
  }

  public void invoke() {
    SheetImpl sheet = new SheetImpl(_poiSheet);
    ContextImpl ctx = new ContextImpl(sheet);

    _headerExtractor.onExtract(ctx);
  }

  private final ConfigHeaderExtractor _headerExtractor;

  private final Sheet _poiSheet;
}
