package com.cilamp;

import java.util.Timer;
import java.util.TimerTask;

import com.cilamp.event.BuildStatusLoadedEvent;
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
    CILampGui view = new CILampGui();
    errorReporter.initialize(view, trayService);
    mainGui.initialize(view, eventBus, errorReporter);

    trayService.setMainGui(mainGui);
    trayService.init();

    eventBus.addHandler(LampTurnedOnEvent.TYPE, new LampTurnedOnHandler(view));
    eventBus
        .addHandler(LampTurnedOffEvent.TYPE, new LampTurnedOffHandler(view));
    eventBus.addHandler(BuildStatusLoadedEvent.TYPE,
        new BuildStatusLoadedHandler(view));

    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        try {
          System.out.println("Checking build");
          buildStatusService.getLastCompletedBuildStatus();
        } catch (Exception exception) {
          errorReporter.notifyError(exception);
          // TODO add log here
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
