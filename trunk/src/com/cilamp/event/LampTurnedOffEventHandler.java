package com.cilamp.event;

import com.cilamp.event.base.CILampEventHandler;

public interface LampTurnedOffEventHandler extends CILampEventHandler {

  void onLampTurnedOff(LampTurnedOffEvent lampTurnedOffEvent);

}
