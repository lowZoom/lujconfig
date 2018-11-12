package luj.config.maven.plugin.excel.sheet.tomap.json

import luj.config.maven.plugin.excel.sheet.tomap.merge.column.SheetColumnMerger

interface JsonValueMaker {

  abstract class Factory {

    static JsonValueMaker create(SheetColumnMerger.Field field, List value) {
      def header = new HeaderImpl(field.getChildren())
      return new JsonValueMakerImpl(header, value)
    }
  }

  List make()
}
