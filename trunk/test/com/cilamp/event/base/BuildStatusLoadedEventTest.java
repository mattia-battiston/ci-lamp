package com.cilamp.event.base;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.cilamp.event.BuildStatusLoadedEvent;
import com.cilamp.event.BuildStatusLoadedEventHandler;
import com.cilamp.model.Build;

public class BuildStatusLoadedEventTest {

  private BuildStatusLoadedEvent buildStatusLoadedEvent = new BuildStatusLoadedEvent(
      new Build());

  @Test
  public void dispatchToEventHandler() {
    BuildStatusLoadedEventHandler handler = mock(BuildStatusLoadedEventHandler.class);

    buildStatusLoadedEvent.dispatch(handler);

    verify(handler).onBuildStatusLoaded(buildStatusLoadedEvent);
  }

}
