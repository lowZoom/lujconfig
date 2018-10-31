package luj.config.example.business.m03.config;

import java.util.List;

public interface SceneCfg {

  String id();

  String name();

  List<SceneCfg> link();
}
