package com.cilamp;

import com.cilamp.gui.app.CILampGui;
import com.cilamp.gui.tray.CILampTrayService;

public class CILamp {

  private CILampTrayService trayService;
  private CILampGui mainGui;

  public static void main(String[] args) {
    new CILamp().initializeApplication();
  }

  public CILamp() {
    trayService = new CILampTrayService();
    mainGui = new CILampGui();
  }

  public void initializeApplication() {
    trayService.setMainGui(mainGui);
    trayService.init();
  }

  public void setTrayService(CILampTrayService trayService) {
    this.trayService = trayService;
  }

  public void setMainGui(CILampGui mainGui) {
    this.mainGui = mainGui;
  }

}
