package com.cilamp.gui.app;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.cilamp.event.base.EventBus;
import com.cilamp.service.services.LampService;

public class CILampGuiPresenter {

  private final View view;

  private LampService lampService = new LampService();

  private EventBus eventBus;

  interface View {
    Button getAlarmOnButton();

    Button getAlarmOffButton();

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
        view.getAlarmOnButton().setEnabled(false);
        view.getAlarmOffButton().setEnabled(true);
      }
    });
    view.getAlarmOnButton().setEnabled(true);

    view.getAlarmOffButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lampService.turnAlarmOff();
        // TODO fire AlarmTurnedOn event; alarmOff button will be listening for
        // it and it will enable itself
        view.getAlarmOnButton().setEnabled(true);
        view.getAlarmOffButton().setEnabled(false);
      }
    });
    view.getAlarmOffButton().setEnabled(false);
  }

  public void show() {
    view.show();
  }

  public void setLampService(LampService lampService) {
    this.lampService = lampService;
  }

  public void setEventBus(EventBus eventBus) {
    this.eventBus = eventBus;
  }

}
