package com.cilamp.event.base;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.cilamp.event.LampTurnedOnEvent;
import com.cilamp.event.LampTurnedOnEventHandler;

public class LampTurnedOnEventTest {

  private LampTurnedOnEvent lampTurnedOnEvent = new LampTurnedOnEvent();

  @Test
  public void dispatchToEventHandler() {
    LampTurnedOnEventHandler handler = mock(LampTurnedOnEventHandler.class);

    lampTurnedOnEvent.dispatch(handler);

    verify(handler).onLampTurnedOn(lampTurnedOnEvent);
  }

}
