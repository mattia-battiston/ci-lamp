package com.cilamp;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cilamp.event.BuildStatusLoadedEvent;
import com.cilamp.event.ErrorEvent;
import com.cilamp.event.LampTurnedOffEvent;
import com.cilamp.event.LampTurnedOnEvent;
import com.cilamp.event.base.EventBus;
import com.cilamp.gui.app.BuildStatusLoadedHandler;
import com.cilamp.gui.app.CILampGui;
import com.cilamp.gui.app.CILampGuiPresenter;
import com.cilamp.gui.app.LampTurnedOffHandler;
import com.cilamp.gui.app.LampTurnedOnHandler;
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

    eventBus.addHandler(LampTurnedOnEvent.TYPE, new LampTurnedOnHandler(view));
    eventBus
        .addHandler(LampTurnedOffEvent.TYPE, new LampTurnedOffHandler(view));
    eventBus.addHandler(BuildStatusLoadedEvent.TYPE,
        new BuildStatusLoadedHandler(view));

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
          System.out.println("Checking build");
          buildStatusService.getLastCompletedBuildStatus();
        } catch (Exception exception) {
          // TODO fire ErrorEvent instead of calling errorReporter directly
          errorReporter.notifyError(exception);
          log.error("Error retrieving build information", exception);
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
