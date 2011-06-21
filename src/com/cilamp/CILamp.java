package com.cilamp;

import com.cilamp.gui.app.CILampGui;
import com.cilamp.gui.app.CILampGuiPresenter;
import com.cilamp.gui.tray.CILampTrayService;

public class CILamp {

  private CILampTrayService trayService;
  private CILampGuiPresenter mainGui;

  public static void main(String[] args) {
    new CILamp().initializeApplication();
  }

  public CILamp() {
    trayService = new CILampTrayService();
    mainGui = new CILampGuiPresenter(new CILampGui());
  }

  public void initializeApplication() {
    trayService.setMainGui(mainGui);
    trayService.init();
  }

  public void setTrayService(CILampTrayService trayService) {
    this.trayService = trayService;
  }

  public void setMainGui(CILampGuiPresenter mainGui) {
    this.mainGui = mainGui;
  }

}
