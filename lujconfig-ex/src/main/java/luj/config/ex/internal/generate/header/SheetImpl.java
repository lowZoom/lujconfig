package luj.config.ex.internal.generate.header;

import luj.config.ex.api.extract.ConfigHeaderExtractor;
import org.apache.poi.ss.usermodel.Sheet;

final class SheetImpl implements ConfigHeaderExtractor.Sheet {

  SheetImpl(Sheet poiSheet) {
    _poiSheet = poiSheet;
  }

  @Override
  public String getName() {
    return _poiSheet.getSheetName();
  }

  private final Sheet _poiSheet;
}
