package luj.config.internal.cache.link;

import java.util.Map;
import org.omg.CORBA.NO_IMPLEMENT;

public interface ConfigInstanceLinker {

  interface Factory {

    static ConfigInstanceLinker create() {
      throw new NO_IMPLEMENT("create尚未实现");
    }
  }

  Map<Class<?>, Map<String, Object>> link();
}
