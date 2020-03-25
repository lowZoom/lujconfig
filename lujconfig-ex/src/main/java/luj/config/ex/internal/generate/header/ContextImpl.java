package luj.config.ex.internal.generate.header;

import luj.config.ex.api.extract.ConfigHeaderExtractor;

final class ContextImpl implements ConfigHeaderExtractor.Context {

  ContextImpl(ConfigHeaderExtractor.Sheet sheet) {
    _sheet = sheet;
  }

  @Override
  public ConfigHeaderExtractor.Sheet getSheet() {
    return _sheet;
  }

  private final ConfigHeaderExtractor.Sheet _sheet;
}
