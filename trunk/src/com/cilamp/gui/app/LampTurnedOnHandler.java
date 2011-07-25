package com.cilamp.gui.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cilamp.event.BuildFailedEvent;
import com.cilamp.event.BuildFailedEventHandler;

public class LampTurnedOnHandler implements BuildFailedEventHandler {

  final Logger log = LoggerFactory.getLogger(LampTurnedOnHandler.class);

  private CILampGuiPresenter.View view;

  public LampTurnedOnHandler(CILampGuiPresenter.View view) {
    this.view = view;
  }

  @Override
  public void onBuildFailed(BuildFailedEvent event) {
    log.info("Lamp turned on, handling event");
    view.getAlarmOnButton().setEnabled(false);
    view.getAlarmOffButton().setEnabled(true);
  }

}
