package com.cilamp.gui.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cilamp.event.LampTurnedOffEvent;
import com.cilamp.event.LampTurnedOffEventHandler;

public class LampTurnedOffHandler implements LampTurnedOffEventHandler {

  final Logger log = LoggerFactory.getLogger(LampTurnedOffHandler.class);

  private CILampGuiPresenter.View view;

  public LampTurnedOffHandler(CILampGuiPresenter.View view) {
    this.view = view;
  }

  @Override
  public void onLampTurnedOff(LampTurnedOffEvent lampTurnedOffEvent) {
    log.info("Lamp turned off, handling event");
    view.getAlarmOnButton().setEnabled(true);
    view.getAlarmOffButton().setEnabled(false);
  }

}
