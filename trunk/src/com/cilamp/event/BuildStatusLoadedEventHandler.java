package com.cilamp.event;

import com.cilamp.event.base.CILampEventHandler;

public interface BuildStatusLoadedEventHandler extends CILampEventHandler {

  void onBuildStatusLoaded(BuildStatusLoadedEvent buildStatusLoadedEvent);

}
