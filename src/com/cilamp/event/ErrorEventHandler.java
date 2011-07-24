package com.cilamp.event;

import com.cilamp.event.base.CILampEventHandler;

public interface ErrorEventHandler extends CILampEventHandler {

  void onError(ErrorEvent errorEvent);

}
