package com.cilamp.event.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cilamp.event.base.CILampEvent.Type;

public class EventBus {

  private final HashMap<CILampEvent.Type<?>, ArrayList<?>> handlersMap = new HashMap<CILampEvent.Type<?>, ArrayList<?>>();

  public EventBus() {
    EventBusInstance.setEventBus(this);
  }

  public <H extends CILampEventHandler> void addHandler(
      CILampEvent.Type<H> type, H handler) {
    ArrayList<H> handlers = getOrInitialize(type);
    handlers.add(handler);
  }

  private <H> ArrayList<H> getOrInitialize(CILampEvent.Type<H> type) {
    if (!handlersMap.containsKey(type))
      handlersMap.put(type, new ArrayList<H>());
    return get(type);
  }

  @SuppressWarnings("unchecked")
  private <H> ArrayList<H> get(CILampEvent.Type<H> type) {
    return (ArrayList<H>) handlersMap.get(type);
  }

  public <H extends CILampEventHandler> void fireEvent(CILampEvent<H> event) {
    Type<H> eventType = event.getAssociatedType();
    List<H> handlers = get(eventType);
    for (H handler : handlers) {
      event.dispatch(handler);
    }
  }

}
