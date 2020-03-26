package luj.config.ex.api.extract;

public interface HeaderColumnExtractor {

  interface Context {

    Column getColumn();

    Return returnColumn();
  }

  interface Column {

    Cell getCell(int row);
  }

  interface Cell {

    String getStringValue();
  }

  interface Return {

    Return fieldName(String val);

    Return dataType(Class<?> val);
  }

  Return onExtract(Context ctx);
}
