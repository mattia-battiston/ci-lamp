package com.cilamp.gui.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cilamp.event.BuildStatusLoadedEvent;
import com.cilamp.event.BuildStatusLoadedEventHandler;
import com.cilamp.model.Build;
import com.cilamp.service.services.LampService;

public class NotifyLampAfterBuildStatusLoadedHandler implements
    BuildStatusLoadedEventHandler {

  final Logger log = LoggerFactory
      .getLogger(NotifyLampAfterBuildStatusLoadedHandler.class);

  private LampService lampService = new LampService();

  @Override
  public void onBuildStatusLoaded(BuildStatusLoadedEvent buildStatusLoadedEvent) {
    log.info("build loaded, notifying lamp of current status");
    Build build = buildStatusLoadedEvent.getBuild();

    if ("SUCCESS".equals(build.getStatus()))
      lampService.turnAlarmOff();
    else
      lampService.turnAlarmOn();
  }

  public void setLampService(LampService lampService) {
    this.lampService = lampService;
  }
}
