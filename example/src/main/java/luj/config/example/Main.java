package luj.config.example;

import luj.config.example.business.m03.ui.SceneUi;

final class Main {

  public static void main(String[] args) {
    new Main().start();
  }

  private void start() {
    SceneUi.Factory.create().show();
  }
}
