package com.cilamp.gui.app;

import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.event.BuildStatusLoadedEvent;
import com.cilamp.model.Build;

public class RefreshViewAfterBuildStatusLoadedHandlerTest {

  private RefreshViewAfterBuildStatusLoadedHandler handler;

  @Mock
  private CILampGuiPresenter.View view;

  private BuildStatusLoadedEvent buildStatusLoadedEvent;

  private Build build = new Build();

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    handler = new RefreshViewAfterBuildStatusLoadedHandler(view);
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
    build.setNumber("123");

    handler.onBuildStatusLoaded(buildStatusLoadedEvent);

    verify(view).setBuildNumber("123");
  }

  @Test
  public void displayBuildUrl() {
    build.setUrl("www.build.com");

    handler.onBuildStatusLoaded(buildStatusLoadedEvent);

    verify(view).setBuildUrl("www.build.com");
  }

  @Test
  public void displayBuildCommitters() {
    build.setCommitters(new HashSet<String>(Arrays.asList("user1", "user2",
        "user3")));

    handler.onBuildStatusLoaded(buildStatusLoadedEvent);

    verify(view).setBuildCommitters("user2, user1, user3");
  }

  @Test
  public void emptyCommittersWhenNoCommitters() {
    build.setCommitters(new HashSet<String>());

    handler.onBuildStatusLoaded(buildStatusLoadedEvent);

    verify(view).setBuildCommitters("");
  }

}
