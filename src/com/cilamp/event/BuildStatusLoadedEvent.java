package com.cilamp.event;

import com.cilamp.event.base.CILampEvent;
import com.cilamp.model.Build;

public class BuildStatusLoadedEvent implements
    CILampEvent<BuildStatusLoadedEventHandler> {

  public static Type<BuildStatusLoadedEventHandler> TYPE = new Type<BuildStatusLoadedEventHandler>();

  private Build build;

  public BuildStatusLoadedEvent(Build build) {
    this.build = build;
  }

  @Override
  public Type<BuildStatusLoadedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(BuildStatusLoadedEventHandler handler) {
    handler.onBuildStatusLoaded(this);
  }

  public Build getBuild() {
    return build;
  }

}
