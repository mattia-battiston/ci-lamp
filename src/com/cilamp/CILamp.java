package com.cilamp;

import com.cilamp.event.base.EventBus;
import com.cilamp.gui.app.CILampGui;
import com.cilamp.gui.app.CILampGuiPresenter;
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
    mainGui.initialize(new CILampGui(), eventBus);

    trayService.setMainGui(mainGui);
    trayService.init();
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
