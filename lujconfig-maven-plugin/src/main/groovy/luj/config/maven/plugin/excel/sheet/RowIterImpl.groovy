package luj.config.maven.plugin.excel.sheet

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class RowIterImpl implements Iterator<TableRowMerger.Row> {

  @Override
  boolean hasNext() {
    return _iter.hasNext()
  }

  @Override
  TableRowMerger.Row next() {
    return new RowImpl(_iter.next())
  }

  private Iterator _iter
}
