package com.cilamp.gui.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cilamp.event.BuildSucceededEvent;
import com.cilamp.event.BuildSucceededEventHandler;

public class LampTurnedOffHandler implements BuildSucceededEventHandler {

  final Logger log = LoggerFactory.getLogger(LampTurnedOffHandler.class);

  private CILampGuiPresenter.View view;

  public LampTurnedOffHandler(CILampGuiPresenter.View view) {
    this.view = view;
  }

  @Override
  public void onBuildSucceeded(BuildSucceededEvent lampTurnedOffEvent) {
    log.info("Lamp turned off, handling event");
    view.getBuildFailedButton().setEnabled(true);
    view.getBuildSucceededButton().setEnabled(false);
  }

}
