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
    view.getAlarmOnButton().setEnabled(true);
    view.getAlarmOffButton().setEnabled(false);
  }

}
