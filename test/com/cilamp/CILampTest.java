package com.cilamp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cilamp.event.BuildStatusLoadedEvent;
import com.cilamp.event.ErrorEvent;
import com.cilamp.event.BuildSucceededEvent;
import com.cilamp.event.BuildFailedEvent;
import com.cilamp.event.base.EventBus;
import com.cilamp.gui.app.CILampGui;
import com.cilamp.gui.app.CILampGuiPresenter;
import com.cilamp.gui.app.BuildSucceededHandler;
import com.cilamp.gui.app.BuildFailedHandler;
import com.cilamp.gui.app.NotifyLampAfterBuildStatusLoadedHandler;
import com.cilamp.gui.app.RefreshViewAfterBuildStatusLoadedHandler;
import com.cilamp.gui.tray.CILampTrayService;
import com.cilamp.service.services.BuildStatusService;
import com.cilamp.service.services.ErrorReporterService;
import com.cilamp.service.services.PropertiesService;

public class CILampTest {

  @Mock
  private CILampTrayService ciLampTrayService;

  @Mock
  private CILampGuiPresenter ciLampGui;

  @Mock
  private EventBus bus;

  @Mock
  private ErrorReporterService errorReporterService;

  @Mock
  private Timer timer;

  @Mock
  private BuildStatusService buildStatusService;

  @Mock
  private PropertiesService propertiesService;

  private CILamp ciLamp = new CILamp();

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    ciLamp.setMainGui(ciLampGui);
    ciLamp.setTrayService(ciLampTrayService);
    ciLamp.setEventBus(bus);
    ciLamp.setErrorReporter(errorReporterService);
    ciLamp.setTimer(timer);
    ciLamp.setBuildStatusService(buildStatusService);
    ciLamp.setPropertiesService(propertiesService);
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
  public void initializeErrorReporter() {
    ciLamp.initializeApplication();

    verify(errorReporterService).initialize(any(CILampGuiPresenter.View.class),
        eq(ciLampTrayService));
  }

  @Test
  public void mainGuiIsInitialized() {
    ciLamp.initializeApplication();

    verify(ciLampGui).initialize(any(CILampGui.class), eq(bus));
  }

  @Test
  public void setHandlerForLampTurnedOnEvent() {
    ciLamp.initializeApplication();

    verify(bus).addHandler(eq(BuildFailedEvent.TYPE),
        any(BuildFailedHandler.class));
  }

  @Test
  public void setHandlerForLampTurnedOffEvent() {
    ciLamp.initializeApplication();

    verify(bus).addHandler(eq(BuildSucceededEvent.TYPE),
        any(BuildSucceededHandler.class));
  }

  @Test
  public void setHandlerToRefreshTheViewAfterBuildIsLoaded() {
    ciLamp.initializeApplication();

    verify(bus, atLeastOnce()).addHandler(eq(BuildStatusLoadedEvent.TYPE),
        any(RefreshViewAfterBuildStatusLoadedHandler.class));
  }

  @Test
  public void setHandlerToNotifyTheLampAdterBuildIsLoaded() {
    ciLamp.initializeApplication();

    verify(bus, atLeastOnce()).addHandler(eq(BuildStatusLoadedEvent.TYPE),
        any(NotifyLampAfterBuildStatusLoadedHandler.class));
  }

  @Test
  public void setErrorEventHandler() {
    ciLamp.initializeApplication();

    verify(bus).addHandler(eq(ErrorEvent.TYPE), eq(errorReporterService));
  }

  @Test
  public void scheduleBuildStatusReader() {
    mockBuildCheckPeriodProperty(999L);

    ciLamp.initializeApplication();

    verify(timer).schedule(any(TimerTask.class), eq(1000L), eq(999L));
  }

  @Test
  public void taskCallsServiceToReadBuildStatus() {
    ciLamp.initializeApplication();

    TimerTask task = getTaskScheduled();
    task.run();

    verify(buildStatusService).getLastCompletedBuildStatus();
  }

  @Test
  public void exceptionsExecutingTaskAreReported() {
    RuntimeException exception = throwExceptionWhenExecutingTask();

    ciLamp.initializeApplication();
    TimerTask task = getTaskScheduled();
    task.run();

    ErrorEvent eventFired = errorEventIsFired();
    assertThat((RuntimeException) eventFired.getError(), is(exception));
  }

  private void mockBuildCheckPeriodProperty(long buildCheckPeriod) {
    when(propertiesService.getRefreshPeriod()).thenReturn(buildCheckPeriod);
  }

  private RuntimeException throwExceptionWhenExecutingTask() {
    RuntimeException exception = new RuntimeException("test exception");
    when(buildStatusService.getLastCompletedBuildStatus()).thenThrow(exception);
    return exception;
  }

  private TimerTask getTaskScheduled() {
    ArgumentCaptor<TimerTask> task = ArgumentCaptor.forClass(TimerTask.class);
    verify(timer).schedule(task.capture(), anyLong(), anyLong());
    return task.getValue();
  }

  private ErrorEvent errorEventIsFired() {
    ArgumentCaptor<ErrorEvent> eventController = ArgumentCaptor
        .forClass(ErrorEvent.class);
    verify(bus).fireEvent(eventController.capture());
    ErrorEvent eventFired = eventController.getValue();
    return eventFired;
  }

}
