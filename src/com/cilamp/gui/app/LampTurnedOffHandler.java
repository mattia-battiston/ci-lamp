package com.cilamp.gui.app;

import com.cilamp.event.LampTurnedOffEvent;
import com.cilamp.event.LampTurnedOffEventHandler;

public class LampTurnedOffHandler implements LampTurnedOffEventHandler {

  private CILampGuiPresenter.View view;

  public LampTurnedOffHandler(CILampGuiPresenter.View view) {
    this.view = view;
  }

  @Override
  public void onLampTurnedOff(LampTurnedOffEvent lampTurnedOffEvent) {
    // TODO fire AlarmTurnedOn event; alarmOff button will be listening for
    // it and it will enable itself
    view.getAlarmOnButton().setEnabled(true);
    view.getAlarmOffButton().setEnabled(false);
  }

}
