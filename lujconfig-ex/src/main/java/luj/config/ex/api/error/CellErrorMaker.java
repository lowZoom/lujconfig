package luj.config.ex.api.error;

import java.nio.file.Path;

public interface CellErrorMaker {

  interface Context {

    Path getWorkbookPath();

    String getSheetName();

    int getRowNum();

    int getColumnNum();
  }

  RuntimeException make(Context ctx);
}
