package luj.config.maven.plugin.excel.sheet

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class DataImpl implements SheetToMapConverterImpl.Data {

  @Override
  Object getColumn(int index) {
    return _columnList[index]
  }

  private List _columnList
}
