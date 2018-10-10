package luj.config.maven.plugin.excel.sheet.merge

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class RowIterImpl implements Iterator<TableRowMergerImpl.Row> {

  @Override
  boolean hasNext() {
    return _iter.hasNext()
  }

  @Override
  TableRowMergerImpl.Row next() {
    return new RowImpl(_iter.next())
  }

  private Iterator _iter
}
