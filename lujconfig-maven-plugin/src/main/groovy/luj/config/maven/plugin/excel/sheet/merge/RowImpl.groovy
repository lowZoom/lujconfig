package luj.config.maven.plugin.excel.sheet.merge

import groovy.transform.PackageScope
import luj.groovy.AutoCtor
import org.apache.poi.ss.usermodel.Row

@PackageScope
@AutoCtor
class RowImpl implements TableRowMergerImpl.Row {

  @Override
  String getColumn(int index) {
    return _row.getCell(index)
  }

  @Override
  int countColumn() {
    return _row.lastCellNum
  }

  private Row _row
}