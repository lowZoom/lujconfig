package luj.config.maven.plugin.excel.sheet.merge

import org.apache.poi.ss.usermodel.Row

interface TableRowMerger {

  abstract class Factory {

    static TableRowMerger create(Iterator<Row> rowIter) {
      return new TableRowMergerImpl(new RowIterImpl(rowIter))
    }
  }

  List merge()
}
