package luj.config.example.business.m03.ui;

import static com.google.common.base.Preconditions.checkNotNull;

import luj.config.api.ConfigSession;
import luj.config.example.business.m03.config.SceneCfg;

final class SceneUiImpl implements SceneUi {

  SceneUiImpl(ConfigSession configSession, String sceneId) {
    _configSession = configSession;
    _sceneId = sceneId;
  }

  @Override
  public void show() {
    System.out.println("你出生了。");

    SceneCfg sceneCfg = _configSession.findConfig(SceneCfg.class, _sceneId);
    checkNotNull(sceneCfg, _sceneId);

    System.out.println("你所在的位置：<" + sceneCfg.name() + ">");

    System.out.println("请选择移动目标：");
    for (SceneCfg link : sceneCfg.link()) {
      System.out.println("[" + link.id() + "]" + link.name());
    }
  }

  private final ConfigSession _configSession;

  private String _sceneId;
}
