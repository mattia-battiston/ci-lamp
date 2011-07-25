package com.cilamp.event;

import com.cilamp.event.base.CILampEventHandler;

public interface BuildSucceededEventHandler extends CILampEventHandler {

  void onBuildSucceeded(BuildSucceededEvent lampTurnedOffEvent);

}
