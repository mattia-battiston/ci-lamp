package com.cilamp.event;

import com.cilamp.event.base.CILampEventHandler;


public interface LampTurnedOnEventHandler extends CILampEventHandler {

  void onLampTurnedOn(LampTurnedOnEvent event);

}
