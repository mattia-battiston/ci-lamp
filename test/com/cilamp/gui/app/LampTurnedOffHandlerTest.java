package com.cilamp.gui.app;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Button;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LampTurnedOffHandlerTest {

  private LampTurnedOffHandler handler;

  @Mock
  private CILampGuiPresenter.View view;

  @Mock
  private Button alarmOnButton;

  @Mock
  private Button alarmOffButton;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    handler = new LampTurnedOffHandler(view);
    mockView();
  }

  @Test
  public void enableAlarmOnButtonWhenLampTurnsOff() {
    handler.onBuildSucceeded(null);

    verify(alarmOnButton).setEnabled(true);
  }

  @Test
  public void disableAlarmOffButtonWhenLampTurnsOff() {
    handler.onBuildSucceeded(null);

    verify(alarmOffButton).setEnabled(false);
  }

  private void mockView() {
    when(view.getAlarmOnButton()).thenReturn(alarmOnButton);
    when(view.getAlarmOffButton()).thenReturn(alarmOffButton);
  }

}
