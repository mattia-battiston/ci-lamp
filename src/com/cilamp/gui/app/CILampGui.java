package com.cilamp.gui.app;

import java.awt.Button;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.cilamp.command.AlarmOnCommand;

public class CILampGui {

  private JFrame app = new JFrame();

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
    container.add(content);

    addAlarmOnButton(content);
    addAlarmOffButton(content);
  }

  private void addAlarmOffButton(JPanel content) {
    Button alarmOff = new Button("Alarm OFF!");
    content.add(alarmOff);
    alarmOff.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new AlarmOnCommand().execute();
        // TODO enable alarmOn
      }
    });
  }

  private void addAlarmOnButton(JPanel content) {
    Button alarmOn = new Button("Alarm ON!");
    content.add(alarmOn);
    alarmOn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new AlarmOnCommand().execute();
        // TODO enable alarmOff
      }
    });
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
