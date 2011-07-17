package com.cilamp.event;

import com.cilamp.event.base.CILampEvent;

public class BuildStatusLoadedEvent implements
    CILampEvent<BuildStatusLoadedEventHandler> {

  public static Type<BuildStatusLoadedEventHandler> TYPE = new Type<BuildStatusLoadedEventHandler>();

  @Override
  public Type<BuildStatusLoadedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(BuildStatusLoadedEventHandler handler) {
    handler.onBuildStatusLoaded(this);
  }

}
