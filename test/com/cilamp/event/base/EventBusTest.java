package com.cilamp.event.base;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class EventBusTest {

  private EventBus bus = new EventBus();

  boolean eventHandlerCalled = false;

  @Test
  public void invokeHandlerForEvent() {
    bus.addHandler(TestEvent.TYPE, new TestEventHandler() {
      @Override
      public void onTestEvent(TestEvent event) {
        eventHandlerCalled = true;
      }
    });

    bus.fireEvent(new TestEvent());

    assertTrue(eventHandlerCalled);
  }

  @Test
  public void passCorrectEventToHandler() {
    bus.addHandler(TestEvent.TYPE, new TestEventHandler() {
      @Override
      public void onTestEvent(TestEvent event) {
        assertThat(event.getProperty(), is("check this property"));
      }
    });

    TestEvent event = new TestEvent();
    event.setProperty("check this property");
    bus.fireEvent(event);
  }

  @Test
  public void invokeAllHandlersForEvent() {
    bus.addHandler(TestEvent.TYPE, new TestEventHandler() {
      @Override
      public void onTestEvent(TestEvent event) {
        event.increaseNumberOfHandlersExecuted();
      }
    });
    bus.addHandler(TestEvent.TYPE, new TestEventHandler() {
      @Override
      public void onTestEvent(TestEvent event) {
        event.increaseNumberOfHandlersExecuted();
      }
    });

    TestEvent event = new TestEvent();
    bus.fireEvent(event);

    assertThat(event.getNumberOfHandlersExecuted(), is(2));
  }
}
