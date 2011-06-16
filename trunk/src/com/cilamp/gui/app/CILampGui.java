package com.cilamp.gui.app;

import javax.swing.JFrame;

public class CILampGui {

  private JFrame app = new JFrame();

  public CILampGui() {
    initialize();
  }

  public void initialize() {
    app.setSize(200, 200);
    app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    hide();
  }

  public void show() {
    app.setVisible(true);
  }

  public void hide() {
    app.setVisible(false);
  }

  public void setApp(JFrame app) {
    this.app = app;
  }

}
