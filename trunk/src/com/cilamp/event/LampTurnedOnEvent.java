package com.cilamp.event;

import com.cilamp.event.base.CILampEvent;

public class LampTurnedOnEvent implements CILampEvent<LampTurnedOnEventHandler> {

  public static Type<LampTurnedOnEventHandler> TYPE = new Type<LampTurnedOnEventHandler>();

  @Override
  public Type<LampTurnedOnEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(LampTurnedOnEventHandler handler) {
    handler.onLampTurnedOn(this);
  }

}
