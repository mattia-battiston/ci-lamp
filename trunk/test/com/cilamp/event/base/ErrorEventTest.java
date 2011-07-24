package com.cilamp.event.base;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.cilamp.event.ErrorEvent;
import com.cilamp.event.ErrorEventHandler;

public class ErrorEventTest {

  private ErrorEvent errorEvent = new ErrorEvent(new RuntimeException());

  @Test
  public void dispatchToEventHandler() {
    ErrorEventHandler handler = mock(ErrorEventHandler.class);

    errorEvent.dispatch(handler);

    verify(handler).onError(errorEvent);
  }

}
