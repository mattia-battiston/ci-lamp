package com.cilamp.event.base;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.cilamp.event.BuildFailedEvent;
import com.cilamp.event.BuildFailedEventHandler;

public class BuildFailedEventTest {

  private BuildFailedEvent lampTurnedOnEvent = new BuildFailedEvent();

  @Test
  public void dispatchToEventHandler() {
    BuildFailedEventHandler handler = mock(BuildFailedEventHandler.class);

    lampTurnedOnEvent.dispatch(handler);

    verify(handler).onBuildFailed(lampTurnedOnEvent);
  }

}
