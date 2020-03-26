package luj.config.ex.internal.generate.extract.header;

import luj.config.ex.api.extract.ConfigHeaderExtractor;

final class ContextImpl implements ConfigHeaderExtractor.Context {

  ContextImpl(ConfigHeaderExtractor.Sheet sheet) {
    _sheet = sheet;
  }

  @Override
  public ConfigHeaderExtractor.Sheet getSheet() {
    return _sheet;
  }

  @Override
  public ConfigHeaderExtractor.Return returnHeader() {
    return new ExtractReturnImpl();
  }

  private final ConfigHeaderExtractor.Sheet _sheet;
}
