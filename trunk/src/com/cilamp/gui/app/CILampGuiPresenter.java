package com.cilamp.gui.app;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.cilamp.service.command.AlarmOnCommand;

public class CILampGuiPresenter {

  private final View view;

  interface View {
    Button getAlarmOnButton();

    void show();

    void hide();
  }

  public CILampGuiPresenter(View view) {
    this.view = view;
    initialize();
  }

  private void initialize() {
    view.getAlarmOnButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new AlarmOnCommand().execute();
        // TODO enable alarmOff
      }
    });
  }

  public void show() {
    view.show();
  }

}
