package com.cilamp.gui.app;

import static junit.framework.Assert.fail;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.event.BuildStatusLoadedEvent;
import com.cilamp.model.Build;

public class BuildStatusLoadedHandlerTest {

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

  @Test
  public void displayBuildNumber() {
    fail("TODO");
  }

  @Test
  public void displayBuildUrl() {
    fail("TODO");
  }

  @Test
  public void displayBuildCommitters() {
    fail("TODO");
  }

  @Test
  public void displayMessageWhenNoCommitters() {
    fail("TODO");
  }

}
