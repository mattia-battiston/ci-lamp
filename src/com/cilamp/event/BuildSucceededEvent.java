package com.cilamp.event;

import com.cilamp.event.base.CILampEvent;

public class BuildSucceededEvent implements
    CILampEvent<BuildSucceededEventHandler> {

  public static Type<BuildSucceededEventHandler> TYPE = new Type<BuildSucceededEventHandler>();

  @Override
  public Type<BuildSucceededEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(BuildSucceededEventHandler handler) {
    handler.onBuildSucceeded(this);
  }

}
