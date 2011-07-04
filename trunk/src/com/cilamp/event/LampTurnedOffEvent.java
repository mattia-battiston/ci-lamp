package com.cilamp.event;

import com.cilamp.event.base.CILampEvent;

public class LampTurnedOffEvent implements
    CILampEvent<LampTurnedOffEventHandler> {

  public static Type<LampTurnedOffEventHandler> TYPE = new Type<LampTurnedOffEventHandler>();

  @Override
  public Type<LampTurnedOffEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(LampTurnedOffEventHandler handler) {
    handler.onLampTurnedOff(this);
  }

}
