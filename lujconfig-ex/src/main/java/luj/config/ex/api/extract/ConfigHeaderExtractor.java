package luj.config.ex.api.extract;

public interface ConfigHeaderExtractor {

  interface Context {

    Sheet getSheet();
  }

  interface Sheet {

    String getName();
  }

  void onExtract(Context ctx);
}
