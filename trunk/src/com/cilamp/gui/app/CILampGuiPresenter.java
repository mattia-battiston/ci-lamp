package com.cilamp.gui.app;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.cilamp.service.services.LampService;

public class CILampGuiPresenter {

  private final View view;

  private LampService lampService = new LampService();

  interface View {
    Button getAlarmOnButton();

    void show();

    void hide();
  }

  public CILampGuiPresenter(View view) {
    this.view = view;
    bindListenersToView();
  }

  private void bindListenersToView() {
    view.getAlarmOnButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lampService.turnAlarmOn();
        // TODO fire AlarmTurnedOn event; alarmOff button will be listening for
        // it and it will enable itself
      }
    });
  }

  public void show() {
    view.show();
  }

  public void setLampService(LampService lampService) {
    this.lampService = lampService;
  }

}
