package luj.config.ex.api.extract;

public interface ConfigRowExtractor {

  interface Context {

  }


  void onExtract(Context ctx);
}
