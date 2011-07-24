package com.cilamp.gui.app;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.event.BuildStatusLoadedEvent;
import com.cilamp.model.Build;
import com.cilamp.service.services.LampService;

public class NotifyLampAfterBuildStatusLoadedHandlerTest {

  private NotifyLampAfterBuildStatusLoadedHandler handler;

  @Mock
  private LampService lampService;

  private BuildStatusLoadedEvent buildStatusLoadedEvent;

  private Build build = new Build();

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    handler = new NotifyLampAfterBuildStatusLoadedHandler();
    buildStatusLoadedEvent = new BuildStatusLoadedEvent(build);
    handler.setLampService(lampService);
  }

  @Test
  public void notifyLampWhenBuildSucceded() {
    build.setStatus("SUCCESS");

    handler.onBuildStatusLoaded(buildStatusLoadedEvent);

    verify(lampService).turnAlarmOff();
  }

  @Test
  public void notifyLampWhenBuildFailed() {
    build.setStatus("FAILED");

    handler.onBuildStatusLoaded(buildStatusLoadedEvent);

    verify(lampService).turnAlarmOn();
  }

  @Test
  public void buildSucceddedOnlyWhenStatusIsSuccess() {
    build.setStatus("OK");

    handler.onBuildStatusLoaded(buildStatusLoadedEvent);

    verify(lampService).turnAlarmOn();
  }

}
