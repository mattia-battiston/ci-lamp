package com.cilamp;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.gui.app.CILampGuiPresenter;
import com.cilamp.gui.tray.CILampTrayService;

public class CILampTest {

  @Mock
  private CILampTrayService ciLampTrayService;

  @Mock
  private CILampGuiPresenter ciLampGui;

  private CILamp ciLamp = new CILamp();

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    ciLamp.setMainGui(ciLampGui);
    ciLamp.setTrayService(ciLampTrayService);
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

}
