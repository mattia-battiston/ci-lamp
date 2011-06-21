package com.cilamp.gui.app;

import java.awt.Button;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.cilamp.gui.factory.PanelFactory;

public class CILampGui implements CILampGuiPresenter.View {

  private JFrame app = new JFrame();

  private PanelFactory panelFactory = new PanelFactory();

  private Button alarmOnButton;

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
    JPanel content = panelFactory.createPanel();
    container.add(content);

    addAlarmOnButton(content);
  }

  private void addAlarmOnButton(JPanel content) {
    alarmOnButton = new Button("Alarm ON!");
    content.add(alarmOnButton);
  }

  @Override
  public void show() {
    app.setVisible(true);
  }

  @Override
  public void hide() {
    app.setVisible(false);
  }

  @Override
  public Button getAlarmOnButton() {
    return alarmOnButton;
  }

  public void setApp(JFrame app) {
    this.app = app;
  }

  public void setPanelFactory(PanelFactory panelFactory) {
    this.panelFactory = panelFactory;
  }

}
