package luj.config.example.business.m02.config;

import java.util.List;
import java.util.Map;

public interface MonsterCfg {

  String id();

  String name();

  List<Act> act();

  List<String> pas();

  String sp();

  Map<String, Double> attr();

  interface Act {

    String id();

    double prob();
  }
}
