package luj.config.maven.plugin.excel.sheet

import groovy.transform.PackageScope
import luj.config.maven.plugin.excel.sheet.merge.column.SheetColumnMerger
import luj.config.maven.plugin.excel.sheet.merge.row.TableRowMerger
import luj.groovy.AutoCtor
import org.apache.poi.ss.usermodel.Row

@PackageScope
@AutoCtor
class SheetImpl implements SheetToMapConverterImpl.Sheet {

  @Override
  List<SheetToMapConverterImpl.DataRow> getRowList() {
    return TableRowMerger.Factory.create(_dataIter, _headerList).merge()
        .collect { new DataRowImpl(_headerList, it) }
  }

  @Override
  int getColumnCount() {
    return _headerList.size()
  }

  private List<SheetColumnMerger.Field> _headerList

  private Iterator<Row> _dataIter
}
