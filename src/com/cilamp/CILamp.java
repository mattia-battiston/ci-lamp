package com.cilamp;

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

public class CILamp {

  private CILampTrayService trayService;
  private CILampGuiPresenter mainGui;
  private EventBus eventBus;

  public static void main(String[] args) {
    new CILamp().initializeApplication();
  }

  public CILamp() {
    eventBus = new EventBus();
    trayService = new CILampTrayService();
    mainGui = new CILampGuiPresenter();
  }

  public void initializeApplication() {
    CILampGui view = new CILampGui();
    mainGui.initialize(view, eventBus);

    trayService.setMainGui(mainGui);
    trayService.init();

    eventBus.addHandler(LampTurnedOnEvent.TYPE, new LampTurnedOnHandler(view));
    eventBus
        .addHandler(LampTurnedOffEvent.TYPE, new LampTurnedOffHandler(view));
    eventBus.addHandler(BuildStatusLoadedEvent.TYPE,
        new BuildStatusLoadedHandler(view));
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

}
