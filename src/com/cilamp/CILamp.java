package com.cilamp;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cilamp.event.BuildFailedEvent;
import com.cilamp.event.BuildStatusLoadedEvent;
import com.cilamp.event.BuildSucceededEvent;
import com.cilamp.event.ErrorEvent;
import com.cilamp.event.base.EventBus;
import com.cilamp.gui.app.BuildFailedHandler;
import com.cilamp.gui.app.BuildSucceededHandler;
import com.cilamp.gui.app.CILampGui;
import com.cilamp.gui.app.CILampGuiPresenter;
import com.cilamp.gui.app.NotifyLampAfterBuildStatusLoadedHandler;
import com.cilamp.gui.app.RefreshViewAfterBuildStatusLoadedHandler;
import com.cilamp.gui.tray.CILampTrayService;
import com.cilamp.service.services.BuildStatusService;
import com.cilamp.service.services.ErrorReporterService;
import com.cilamp.service.services.PropertiesService;

public class CILamp {

  final Logger log = LoggerFactory.getLogger(CILamp.class);

  private static final long FIRST_TIME_DELAY = 1000L;

  private CILampTrayService trayService;
  private CILampGuiPresenter mainGui;
  private EventBus eventBus;
  private ErrorReporterService errorReporter;
  private Timer timer;
  private BuildStatusService buildStatusService;
  private PropertiesService propertiesService;

  public static void main(String[] args) {
    new CILamp().initializeApplication();
  }

  public CILamp() {
    eventBus = new EventBus();
    trayService = new CILampTrayService();
    mainGui = new CILampGuiPresenter();
    errorReporter = new ErrorReporterService();
    timer = new Timer();
    buildStatusService = new BuildStatusService();
    propertiesService = new PropertiesService();
  }

  public void initializeApplication() {
    log.info("Starting CILamp");

    CILampGui view = new CILampGui();
    mainGui.initialize(view, eventBus);

    trayService.setMainGui(mainGui);
    trayService.init();

    eventBus.addHandler(BuildFailedEvent.TYPE, new BuildFailedHandler(view));
    eventBus.addHandler(BuildSucceededEvent.TYPE, new BuildSucceededHandler(
        view));
    eventBus.addHandler(BuildStatusLoadedEvent.TYPE,
        new RefreshViewAfterBuildStatusLoadedHandler(view));
    eventBus.addHandler(BuildStatusLoadedEvent.TYPE,
        new NotifyLampAfterBuildStatusLoadedHandler());

    errorReporter.initialize(view, trayService);
    eventBus.addHandler(ErrorEvent.TYPE, errorReporter);

    scheduleBuildChecker();
  }

  private void scheduleBuildChecker() {
    log.info("going to check the build every {} ms",
        propertiesService.getRefreshPeriod());
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        try {
          log.info("Checking build status");
          buildStatusService.getLastCompletedBuildStatus();
        } catch (Exception exception) {
          // TODO notify lamp of system error
          log.error("Error retrieving build information", exception);
          eventBus.fireEvent(new ErrorEvent(exception));
        }
      }
    }, FIRST_TIME_DELAY, propertiesService.getRefreshPeriod());
  }

  public void setTrayService(CILampTrayService trayService) {
    this.trayService = trayService;
  }

  public void setMainGui(CILampGuiPresenter mainGui) {
    this.mainGui = mainGui;
  }

  public void setEventBus(EventBus eventBus) {
    this.eventBus = eventBus;
  }

  public void setErrorReporter(ErrorReporterService errorReporter) {
    this.errorReporter = errorReporter;
  }

  public void setTimer(Timer timer) {
    this.timer = timer;
  }

  public void setBuildStatusService(BuildStatusService buildStatusService) {
    this.buildStatusService = buildStatusService;
  }

  public void setPropertiesService(PropertiesService propertiesService) {
    this.propertiesService = propertiesService;
  }

}
