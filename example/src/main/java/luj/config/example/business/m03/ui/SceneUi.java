package luj.config.example.business.m03.ui;

import luj.config.api.ConfigSession;
import luj.config.api.LujConfig;

public interface SceneUi {

  interface Factory {

    static SceneUi create() {
      ConfigSession session = LujConfig.start();
      return new SceneUiImpl(session, "1101");
    }
  }

  void show();
}
