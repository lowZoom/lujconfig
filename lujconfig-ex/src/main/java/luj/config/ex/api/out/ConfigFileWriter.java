package luj.config.ex.api.out;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface ConfigFileWriter {

  interface Context {

    Sheet getSheet();

    Path getWorkbookPath();
  }

  interface Sheet {

    String getName();

    List<Field> getHeader();

    List<Row> getRowList();
  }

  interface Field {

    String getName();

    boolean isPrimaryKey();
  }

  interface Row {

    int getIndex();

    Map<String, Object> getDataMap();
  }

  void onWrite(Context ctx);
}
