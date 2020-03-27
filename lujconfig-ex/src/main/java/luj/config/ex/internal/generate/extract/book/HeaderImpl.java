package luj.config.ex.internal.generate.extract.book;

import java.util.Map;

final class HeaderImpl implements ExcelDataExtractor.Header {

  HeaderImpl(Map<String, ExcelDataExtractor.Field> fieldMap) {
    _fieldMap = fieldMap;
  }

  @Override
  public Map<String, ExcelDataExtractor.Field> getFieldMap() {
    return _fieldMap;
  }

  private final Map<String, ExcelDataExtractor.Field> _fieldMap;
}
