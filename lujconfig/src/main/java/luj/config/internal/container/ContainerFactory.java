package luj.config.internal.container;

import java.util.HashMap;
import luj.bean.api.LujBean;
import luj.config.api.container.ConfigContainer;

public enum ContainerFactory {
  GET;

  public ConfigContainer create() {
    ConfigContainerImpl container = new ConfigContainerImpl();
    container._typeAll = new HashMap<>();
    container._lujbean = LujBean.start();
    return container;
  }
}
