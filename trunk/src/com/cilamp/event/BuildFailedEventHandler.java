package com.cilamp.event;

import com.cilamp.event.base.CILampEventHandler;


public interface BuildFailedEventHandler extends CILampEventHandler {

  void onBuildFailed(BuildFailedEvent event);

}
