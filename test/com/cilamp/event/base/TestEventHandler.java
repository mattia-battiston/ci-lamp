package com.cilamp.event.base;

interface TestEventHandler extends CILampEventHandler {
  void onTestEvent(TestEvent event);
}