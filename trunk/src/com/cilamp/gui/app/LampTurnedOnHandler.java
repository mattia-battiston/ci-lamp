package com.cilamp.gui.app;

import com.cilamp.event.LampTurnedOnEvent;
import com.cilamp.event.LampTurnedOnEventHandler;

public class LampTurnedOnHandler implements LampTurnedOnEventHandler {

  private CILampGuiPresenter.View view;

  public LampTurnedOnHandler(CILampGuiPresenter.View view) {
    this.view = view;
  }

  @Override
  public void onLampTurnedOn(LampTurnedOnEvent event) {
    view.getAlarmOnButton().setEnabled(false);
    view.getAlarmOffButton().setEnabled(true);
  }

}
