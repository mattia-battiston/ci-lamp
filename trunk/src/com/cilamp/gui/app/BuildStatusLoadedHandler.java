package com.cilamp.gui.app;

import java.util.Set;

import com.cilamp.event.BuildStatusLoadedEvent;
import com.cilamp.event.BuildStatusLoadedEventHandler;
import com.cilamp.gui.app.CILampGuiPresenter.View;
import com.cilamp.model.Build;

public class BuildStatusLoadedHandler implements BuildStatusLoadedEventHandler {

  private final View view;

  public BuildStatusLoadedHandler(CILampGuiPresenter.View view) {
    this.view = view;
  }

  @Override
  public void onBuildStatusLoaded(BuildStatusLoadedEvent buildStatusLoadedEvent) {
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
