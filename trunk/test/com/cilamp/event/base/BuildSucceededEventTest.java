package com.cilamp.event.base;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.cilamp.event.BuildSucceededEvent;
import com.cilamp.event.BuildSucceededEventHandler;

public class BuildSucceededEventTest {

  private BuildSucceededEvent buildStatusLoadedEvent = new BuildSucceededEvent();

  @Test
  public void dispatchToEventHandler() {
    BuildSucceededEventHandler handler = mock(BuildSucceededEventHandler.class);

    buildStatusLoadedEvent.dispatch(handler);

    verify(handler).onBuildSucceeded(buildStatusLoadedEvent);
  }

}
