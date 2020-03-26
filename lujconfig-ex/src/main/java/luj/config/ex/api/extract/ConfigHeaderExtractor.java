package luj.config.ex.api.extract;

public interface ConfigHeaderExtractor {

  interface Context {

    Sheet getSheet();

    Return returnHeader();
  }

  interface Sheet {

    String getName();
  }

  interface Return {

    Return dataBeginColumn(int val);

    Return dataBeginRow(int val);
  }

  Return onExtract(Context ctx);
}
