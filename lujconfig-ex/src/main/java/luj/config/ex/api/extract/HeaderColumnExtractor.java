package luj.config.ex.api.extract;

import luj.config.ex.api.error.CellErrorMaker;

public interface HeaderColumnExtractor {

  interface Context {

    Column getColumn();

    Return returnColumn();

    RuntimeException error(CellErrorMaker maker);
  }

  interface Column {

    int getIndex();

    Cell getCell(int row);
  }

  interface Cell {

    String getStringValue();
  }

  interface Return {

    Return fieldName(String val);

    Return dataType(Class<?> val);

    Return asPrimaryKey(boolean val);
  }

  Return onExtract(Context ctx);
}
