package com.cilamp.gui.app;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cilamp.event.BuildStatusLoadedEvent;
import com.cilamp.event.BuildStatusLoadedEventHandler;
import com.cilamp.gui.app.CILampGuiPresenter.View;
import com.cilamp.model.Build;
import com.cilamp.service.services.LampService;

public class BuildStatusLoadedHandler implements BuildStatusLoadedEventHandler {

  final Logger log = LoggerFactory.getLogger(BuildStatusLoadedHandler.class);

  private final View view;

  private LampService lampService;

  public BuildStatusLoadedHandler(CILampGuiPresenter.View view) {
    this.view = view;
  }

  @Override
  public void onBuildStatusLoaded(BuildStatusLoadedEvent buildStatusLoadedEvent) {
    log.info("build loaded, refreshing view");
    Build build = buildStatusLoadedEvent.getBuild();

    view.setBuildResult(build.getStatus());
    view.setBuildNumber(build.getNumber());
    view.setBuildUrl(build.getUrl());
    view.setBuildCommitters(toString(build.getCommitters()));

    // TODO this should stay in a different handler; have one to update the
    // view, and one to update the lamp
    if ("SUCCESS".equals(build.getStatus()))
      lampService.turnAlarmOff();
    else
      lampService.turnAlarmOn();
  }

  private String toString(Set<String> committers) {
    String result = committers.toString();
    return result.substring(1, result.length() - 1);
  }

  public void setLampService(LampService lampService) {
    this.lampService = lampService;
  }
}
