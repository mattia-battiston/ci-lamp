package com.cilamp.event.base;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.cilamp.event.LampTurnedOffEvent;
import com.cilamp.event.LampTurnedOffEventHandler;

public class LampTurnedOffEventTest {

  private LampTurnedOffEvent buildStatusLoadedEvent = new LampTurnedOffEvent();

  @Test
  public void dispatchToEventHandler() {
    LampTurnedOffEventHandler handler = mock(LampTurnedOffEventHandler.class);

    buildStatusLoadedEvent.dispatch(handler);

    verify(handler).onLampTurnedOff(buildStatusLoadedEvent);
  }

}
