package com.cilamp.gui.app;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.event.BuildStatusLoadedEvent;
import com.cilamp.model.Build;

public class BuildStatusLoaderHandlerTest {

  private BuildStatusLoadedHandler handler;

  @Mock
  private CILampGuiPresenter.View view;

  private BuildStatusLoadedEvent buildStatusLoadedEvent;

  private Build build = new Build();

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    handler = new BuildStatusLoadedHandler(view);
    buildStatusLoadedEvent = new BuildStatusLoadedEvent(build);
  }

  @Test
  public void displayStatusAsBuildResult() {
    build.setStatus("SUCCESS");

    handler.onBuildStatusLoaded(buildStatusLoadedEvent);

    verify(view).setBuildResult("SUCCESS");
  }
}
