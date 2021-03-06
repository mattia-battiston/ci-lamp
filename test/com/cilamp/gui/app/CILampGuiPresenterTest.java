package com.cilamp.gui.app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Button;
import java.awt.event.ActionListener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.event.BuildFailedEvent;
import com.cilamp.event.BuildSucceededEvent;
import com.cilamp.event.ErrorEvent;
import com.cilamp.event.base.EventBus;
import com.cilamp.service.services.BuildStatusService;
import com.cilamp.service.services.LampService;

public class CILampGuiPresenterTest {

  private CILampGuiPresenter presenter = new CILampGuiPresenter();

  @Mock
  private CILampGuiPresenter.View view;

  @Mock
  private Button buildFailedButton;

  @Mock
  private Button buildSucceededButton;

  @Mock
  private Button refreshButton;

  @Mock
  private LampService lampService;

  @Mock
  private BuildStatusService buildStatusService;

  @Mock
  private EventBus eventBus;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    mockView();
    presenter.setLampService(lampService);
    presenter.setBuildStatusService(buildStatusService);
    presenter.initialize(view, eventBus);
  }

  @Test
  public void buildFailedCallsService() {
    ActionListener buildFailedListener = getActionListenerForButton(buildFailedButton);
    buildFailedListener.actionPerformed(null);

    verify(lampService).buildFailed();
  }

  @Test
  public void buildFailedFiresEvent() {
    ActionListener buildFailedListener = getActionListenerForButton(buildFailedButton);
    buildFailedListener.actionPerformed(null);

    verify(eventBus).fireEvent(any(BuildFailedEvent.class));
  }

  @Test
  public void exceptionsOnBuildFailedAreReported() {
    Throwable error = throwExceptionNotifyingLampOfFailure();

    ActionListener buildFailedListener = getActionListenerForButton(buildFailedButton);
    buildFailedListener.actionPerformed(null);

    ErrorEvent eventFired = errorEventIsFired();
    assertThat(eventFired.getError(), is(error));
  }

  @Test
  public void buildSucceededCallsService() {
    ActionListener buildSucceededListener = getActionListenerForButton(buildSucceededButton);
    buildSucceededListener.actionPerformed(null);

    verify(lampService).buildSucceeded();
  }

  @Test
  public void buildSucceededFiresEvent() {
    ActionListener buildSucceededListener = getActionListenerForButton(buildSucceededButton);
    buildSucceededListener.actionPerformed(null);

    verify(eventBus).fireEvent(any(BuildSucceededEvent.class));
  }

  @Test
  public void exceptionsOnBuildSucceededAreReported() {
    Throwable error = throwExceptionNotifyingLampOfSuccess();

    ActionListener buildSucceededListener = getActionListenerForButton(buildSucceededButton);
    buildSucceededListener.actionPerformed(null);

    ErrorEvent eventFired = errorEventIsFired();
    assertThat(eventFired.getError(), is(error));
  }

  @Test
  public void refreshCallsService() {
    ActionListener refreshListener = getActionListenerForButton(refreshButton);
    refreshListener.actionPerformed(null);

    verify(buildStatusService).getLastCompletedBuildStatus();
  }

  @Test
  public void exceptionsRefreshingAreReported() {
    RuntimeException exception = throwExceptionWhenLoadingBuild();

    ActionListener refreshListener = getActionListenerForButton(refreshButton);
    refreshListener.actionPerformed(null);

    ErrorEvent eventFired = errorEventIsFired();
    assertThat((RuntimeException) eventFired.getError(), is(exception));
  }

  @Test
  public void showShowsTheView() {
    presenter.show();

    verify(view).show();
  }

  private ErrorEvent errorEventIsFired() {
    ArgumentCaptor<ErrorEvent> eventController = ArgumentCaptor
        .forClass(ErrorEvent.class);
    verify(eventBus).fireEvent(eventController.capture());
    ErrorEvent eventFired = eventController.getValue();
    return eventFired;
  }

  private Throwable throwExceptionNotifyingLampOfSuccess() {
    RuntimeException exception = new RuntimeException("TEST");
    doThrow(exception).when(lampService).buildSucceeded();
    return exception;
  }

  private Throwable throwExceptionNotifyingLampOfFailure() {
    RuntimeException exception = new RuntimeException("TEST");
    doThrow(exception).when(lampService).buildFailed();
    return exception;
  }

  private RuntimeException throwExceptionWhenLoadingBuild() {
    RuntimeException exception = new RuntimeException("test exception");
    when(buildStatusService.getLastCompletedBuildStatus()).thenThrow(exception);
    return exception;
  }

  private ActionListener getActionListenerForButton(Button button) {
    ArgumentCaptor<ActionListener> actionListenerCaptor = ArgumentCaptor
        .forClass(ActionListener.class);
    verify(button).addActionListener(actionListenerCaptor.capture());
    return actionListenerCaptor.getValue();
  }

  private void mockView() {
    when(view.getBuildFailedButton()).thenReturn(buildFailedButton);
    when(view.getBuildSucceededButton()).thenReturn(buildSucceededButton);
    when(view.getRefreshButton()).thenReturn(refreshButton);
  }

}
