package com.cilamp.gui.app;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.cilamp.event.LampTurnedOffEvent;
import com.cilamp.event.LampTurnedOnEvent;
import com.cilamp.event.base.EventBus;
import com.cilamp.service.services.LampService;

public class CILampGuiPresenter {

  private View view;

  private LampService lampService = new LampService();

  private EventBus eventBus;

  public interface View {
    Button getAlarmOnButton();

    Button getAlarmOffButton();

    void show();

    void hide();
  }

  public void initialize(View view, EventBus eventBus) {
    this.view = view;
    this.eventBus = eventBus;

    bindListenersToView();
  }

  private void bindListenersToView() {
    view.getAlarmOnButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lampService.turnAlarmOn();
        eventBus.fireEvent(new LampTurnedOnEvent());
      }
    });
    view.getAlarmOnButton().setEnabled(true);

    view.getAlarmOffButton().addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        lampService.turnAlarmOff();
        eventBus.fireEvent(new LampTurnedOffEvent());
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

}
