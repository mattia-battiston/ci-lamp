package com.cilamp.event.base;

public class EventBusInstance {

  private static EventBus eventBus;

  public static EventBus getEventBus() {
    return eventBus;
  }

  public static void setEventBus(EventBus eventBus) {
    EventBusInstance.eventBus = eventBus;
  }

}
