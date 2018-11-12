package luj.config.maven.plugin.excel.sheet.tomap

import groovy.transform.PackageScope
import luj.config.maven.plugin.excel.sheet.tomap.merge.column.SheetColumnMerger
import luj.config.maven.plugin.excel.sheet.tomap.merge.row.TableRowMerger
import org.apache.poi.ss.usermodel.Row

@PackageScope
class SheetImpl implements SheetToMapConverterImpl.Sheet {

  SheetImpl(List<SheetColumnMerger.Field> headerList, Iterator<Row> dataIter) {
    _headerList = headerList
    _dataIter = dataIter
  }

  @Override
  List<SheetToMapConverterImpl.DataRow> getRowList() {
    return TableRowMerger.Factory.create(_dataIter, _headerList).merge()
        .collect { new DataRowImpl(_headerList, it) }
  }

  @Override
  int getFieldCount() {
    return _headerList.size()
  }

  private final List<SheetColumnMerger.Field> _headerList

  private final Iterator<Row> _dataIter
}
