package com.cilamp.gui.app;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cilamp.event.BuildStatusLoadedEvent;
import com.cilamp.event.BuildStatusLoadedEventHandler;
import com.cilamp.gui.app.CILampGuiPresenter.View;
import com.cilamp.model.Build;

public class RefreshViewAfterBuildStatusLoadedHandler implements
    BuildStatusLoadedEventHandler {

  final Logger log = LoggerFactory
      .getLogger(RefreshViewAfterBuildStatusLoadedHandler.class);

  private final View view;

  public RefreshViewAfterBuildStatusLoadedHandler(CILampGuiPresenter.View view) {
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
  }

  private String toString(Set<String> committers) {
    String result = committers.toString();
    return result.substring(1, result.length() - 1);
  }

}
