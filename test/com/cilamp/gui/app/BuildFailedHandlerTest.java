package com.cilamp.gui.app;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Button;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BuildFailedHandlerTest {

  private BuildFailedHandler handler;

  @Mock
  private CILampGuiPresenter.View view;

  @Mock
  private Button buildFailedButton;

  @Mock
  private Button buildSucceededButton;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    handler = new BuildFailedHandler(view);
    mockView();
  }

  @Test
  public void enableBuildSucceededButtonWhenBuildFails() {
    handler.onBuildFailed(null);

    verify(buildSucceededButton).setEnabled(true);
  }

  @Test
  public void disableBuildFailedButtonWhenBuildFails() {
    handler.onBuildFailed(null);

    verify(buildFailedButton).setEnabled(false);
  }

  private void mockView() {
    when(view.getBuildFailedButton()).thenReturn(buildFailedButton);
    when(view.getBuildSucceededButton()).thenReturn(buildSucceededButton);
  }

}
