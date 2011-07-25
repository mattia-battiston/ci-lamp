package com.cilamp.gui.app;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Button;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BuildSucceededHandlerTest {

  private BuildSucceededHandler handler;

  @Mock
  private CILampGuiPresenter.View view;

  @Mock
  private Button buildFailedButton;

  @Mock
  private Button buildSucceededButton;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    handler = new BuildSucceededHandler(view);
    mockView();
  }

  @Test
  public void enableBuildFailedButtonWhenBuildSucceeds() {
    handler.onBuildSucceeded(null);

    verify(buildFailedButton).setEnabled(true);
  }

  @Test
  public void disableBuildFailedButtonWhenBuildSucceeds() {
    handler.onBuildSucceeded(null);

    verify(buildSucceededButton).setEnabled(false);
  }

  private void mockView() {
    when(view.getBuildFailedButton()).thenReturn(buildFailedButton);
    when(view.getBuildSucceededButton()).thenReturn(buildSucceededButton);
  }

}
