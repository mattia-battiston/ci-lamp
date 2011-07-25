package com.cilamp.gui.app;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Button;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LampTurnedOnHandlerTest {

  private LampTurnedOnHandler handler;

  @Mock
  private CILampGuiPresenter.View view;

  @Mock
  private Button alarmOnButton;

  @Mock
  private Button alarmOffButton;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    handler = new LampTurnedOnHandler(view);
    mockView();
  }

  @Test
  public void enableAlarmOffButtonWhenLampTurnsOn() {
    handler.onBuildFailed(null);

    verify(alarmOffButton).setEnabled(true);
  }

  @Test
  public void disableAlarmOnButtonWhenLampTurnsOn() {
    handler.onBuildFailed(null);

    verify(alarmOnButton).setEnabled(false);
  }

  private void mockView() {
    when(view.getAlarmOnButton()).thenReturn(alarmOnButton);
    when(view.getAlarmOffButton()).thenReturn(alarmOffButton);
  }

}
