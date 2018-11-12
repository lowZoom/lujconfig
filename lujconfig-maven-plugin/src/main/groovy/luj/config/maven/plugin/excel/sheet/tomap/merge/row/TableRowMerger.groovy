package luj.config.maven.plugin.excel.sheet.tomap.merge.row

import org.apache.poi.ss.usermodel.Row

interface TableRowMerger {

  abstract class Factory {

    static TableRowMerger create(Iterator<Row> rowIter, List headerList) {
      return new TableRowMergerImpl(new RowIterImpl(rowIter, headerList))
    }
  }

  List merge()
}
