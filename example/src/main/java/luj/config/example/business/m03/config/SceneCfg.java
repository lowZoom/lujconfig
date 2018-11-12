package luj.config.example.business.m03.config;

import java.util.List;
import luj.config.anno.Config;

@Config("Sheet1")
public interface SceneCfg {

  String id();

  String name();

  List<SceneCfg> link();
}
