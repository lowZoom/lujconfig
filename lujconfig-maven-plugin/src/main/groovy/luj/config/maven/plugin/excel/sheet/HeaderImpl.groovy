package luj.config.maven.plugin.excel.sheet

import groovy.transform.PackageScope
import luj.config.maven.plugin.excel.sheet.merge.TableRowMerger
import luj.groovy.AutoCtor
import org.apache.poi.ss.usermodel.Row

@PackageScope
@AutoCtor
class HeaderImpl implements SheetToMapConverterImpl.Header {

  @Override
  int getColumnCount() {
    return _headerList.size()
  }

  @Override
  String getColumn(int index) {
    return _headerList[index]
  }

  @Override
  List<SheetToMapConverterImpl.Data> getDataList() {
    return TableRowMerger.Factory.create(_dataIter).merge()
        .collect { new DataImpl(it) }
  }

  private List<String> _headerList

  private Iterator<Row> _dataIter
}
