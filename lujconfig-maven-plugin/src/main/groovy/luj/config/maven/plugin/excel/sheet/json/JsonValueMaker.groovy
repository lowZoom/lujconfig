package luj.config.maven.plugin.excel.sheet.json

import luj.config.maven.plugin.excel.sheet.merge.column.SheetColumnMerger

interface JsonValueMaker {

  abstract class Factory {

    static JsonValueMaker create(SheetColumnMerger.Field field, List value) {
      def header = new HeaderImpl(field.getChildren())
      return new JsonValueMakerImpl(header, value)
    }
  }

  List make()
}
