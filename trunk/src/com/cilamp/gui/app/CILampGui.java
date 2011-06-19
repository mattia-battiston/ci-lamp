package com.cilamp.gui.app;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CILampGui {

  private JFrame app = new JFrame();
  private GuiInitializer guiInitializer = new GuiInitializer();

  public CILampGui() {
    initialize();
  }

  public void initialize() {
    app.setSize(200, 200);
    app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    drawGui();
    hide();
  }

  private void drawGui() {
    Container container = app.getContentPane();
    JPanel content = new JPanel();
    guiInitializer.drawGui(content);
    container.add(content);
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

  public void setGuiInitializer(GuiInitializer guiInitializer) {
    this.guiInitializer = guiInitializer;
  }

}
