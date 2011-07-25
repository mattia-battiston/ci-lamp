package com.cilamp.event;

import com.cilamp.event.base.CILampEvent;

public class BuildFailedEvent implements CILampEvent<BuildFailedEventHandler> {

  public static Type<BuildFailedEventHandler> TYPE = new Type<BuildFailedEventHandler>();

  @Override
  public Type<BuildFailedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(BuildFailedEventHandler handler) {
    handler.onBuildFailed(this);
  }

}
