package luj.config.maven.plugin.excel.sheet.json

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class JsonValueMakerImpl implements JsonValueMaker {

  @Override
  List make() {
    if (!_header.isObjectType()) {
      return _rawValueList
    }
    return _rawValueList
        .collect { toMap(it) }
  }

  private Map toMap(List cell) {
    return (0..<_header.getFieldCount())
        .collect { [cell[it], it] }
        .findAll { it[0] }
        .collectEntries { [(_header.getFieldName(it[1])): it[0]] }
  }

  interface Header {

    boolean isObjectType()

    int getFieldCount()

    String getFieldName(int index)
  }

  private Header _header

  private List _rawValueList
}
