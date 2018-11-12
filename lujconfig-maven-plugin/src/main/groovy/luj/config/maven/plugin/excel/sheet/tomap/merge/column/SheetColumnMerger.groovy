package luj.config.maven.plugin.excel.sheet.tomap.merge.column

interface SheetColumnMerger {

  abstract class Factory {

    static SheetColumnMerger create(List<String> headerList) {
      return new SheetColumnMergerImpl((0..<headerList.size())
          .collect { new ExcelColumnImpl(headerList[it], it) }
          .iterator())
    }
  }

  interface Field {

    String getName()

    int getExcelColumnIndex()

    List<Field> getChildren()
  }

  List<Field> merge()
}
