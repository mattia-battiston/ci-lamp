package com.cilamp;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.event.BuildStatusLoadedEvent;
import com.cilamp.event.LampTurnedOffEvent;
import com.cilamp.event.LampTurnedOnEvent;
import com.cilamp.event.base.EventBus;
import com.cilamp.gui.app.BuildStatusLoadedHandler;
import com.cilamp.gui.app.CILampGui;
import com.cilamp.gui.app.CILampGuiPresenter;
import com.cilamp.gui.app.LampTurnedOffHandler;
import com.cilamp.gui.app.LampTurnedOnHandler;
import com.cilamp.gui.tray.CILampTrayService;

public class CILampTest {

  @Mock
  private CILampTrayService ciLampTrayService;

  @Mock
  private CILampGuiPresenter ciLampGui;

  @Mock
  private EventBus bus;

  private CILamp ciLamp = new CILamp();

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    ciLamp.setMainGui(ciLampGui);
    ciLamp.setTrayService(ciLampTrayService);
    ciLamp.setEventBus(bus);
  }

  @Test
  public void initializeApplicationInitsTheTrayService() {
    ciLamp.initializeApplication();

    verify(ciLampTrayService).init();
  }

  @Test
  public void initializeApplicationSetsTheGuiToTheTrayService() {
    ciLamp.initializeApplication();

    verify(ciLampTrayService).setMainGui(ciLampGui);
  }

  @Test
  public void mainGuiIsInitialized() {
    ciLamp.initializeApplication();

    verify(ciLampGui).initialize(any(CILampGui.class), eq(bus));
  }

  @Test
  public void setHandlerForLampTurnedOnEvent() {
    ciLamp.initializeApplication();

    verify(bus).addHandler(eq(LampTurnedOnEvent.TYPE),
        any(LampTurnedOnHandler.class));
  }

  @Test
  public void setHandlerForLampTurnedOffEvent() {
    ciLamp.initializeApplication();

    verify(bus).addHandler(eq(LampTurnedOffEvent.TYPE),
        any(LampTurnedOffHandler.class));
  }

  @Test
  public void setHandlerForBuildStatusLoadedEvent() {
    ciLamp.initializeApplication();

    verify(bus).addHandler(eq(BuildStatusLoadedEvent.TYPE),
        any(BuildStatusLoadedHandler.class));
  }
}
