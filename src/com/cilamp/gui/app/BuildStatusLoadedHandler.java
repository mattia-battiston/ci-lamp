package com.cilamp.gui.app;

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
  }
}
